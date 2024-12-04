import java.util.*;
import java.util.stream.Collectors;

/**
 * The ReferralPathFinder class is responsible for finding the shortest referral path between
 * two students in the StudentGraph. The pathfinding considers connection strengths between
 * students and allows for finding the shortest path to a student who interned at a specified company.
 */
public class ReferralPathFinder {

    private StudentGraph graph;

    /**
     * Constructs a ReferralPathFinder with the given StudentGraph.
     * @param graph the StudentGraph representing relationships and connection strengths between students
     */
    public ReferralPathFinder(StudentGraph graph) {
        // Constructor
        this.graph = graph;
    }

    public static void printReferralPaths(ReferralPathFinder referralPathFinder, UniversityStudent start, String targetCompany) {
        System.out.println("\nReferral Path:");
        List<UniversityStudent> path = referralPathFinder.findReferralPath(start, targetCompany);
        if (path.isEmpty()) {
            System.out.println("No path found to " + targetCompany);
        } else {
            System.out.print("Path to " + targetCompany + ": ");
            System.out.println(path.stream().map(UniversityStudent::getName).collect(Collectors.joining(" -> ")));
        }
    }
    public void printReferralPath(UniversityStudent start, String targetCompany) {
        System.out.print("Referral Path to " + targetCompany + ": ");

        // Find the referral path
        List<UniversityStudent> referralPath = findReferralPath(start, targetCompany);

        // Check if a path was found
        if (referralPath.isEmpty()) {
            System.out.println("No path found to " + targetCompany + ".");
        } else {
            // Format and print the path
            String path = referralPath.stream()
                    .map(UniversityStudent::getName)
                    .collect(Collectors.joining(" -> "));
            System.out.println(path);
        }
    }

    /**
     * Finds the shortest path from the start student to another student who interned at
     * the specified targetCompany.
     * The method uses Dijkstra’s algorithm to traverse the graph, treating stronger connections as
     * "shorter" paths. The pathfinding stops when a student with the target internship is found.
     * @param start        the UniversityStudent from whom the search for the referral path begins
     * @param targetCompany the name of the target company whose intern the path should lead to
     * @return a list of UniversityStudent objects representing the shortest path to the target company,
     *         or an empty list if no path is found
     */
    public List<UniversityStudent> findReferralPath(UniversityStudent start, String targetCompany) {
        Map<UniversityStudent, Double> distances = new HashMap<>(); // Shortest distance to each student
        PriorityQueue<UniversityStudent> queue = new PriorityQueue<>(Comparator.comparingDouble(distances::get));
        Map<UniversityStudent, UniversityStudent> parents = new HashMap<>(); // To reconstruct the path

        // Initialize all distances to infinity, except the start node
        for (UniversityStudent student : graph.getAllNodes()) {
            distances.put(student, Double.POSITIVE_INFINITY);
        }
        distances.put(start, 0.0);
        queue.add(start);

        // Process nodes
        while (!queue.isEmpty()) {
            UniversityStudent current = queue.poll();

            // Check if this student has the target internship
            if (current.getPreviousInternships().contains(targetCompany)) {
                // Reconstruct and return the path
                return reconstructPath(parents, current);
            }

            // Explore neighbors
            for (StudentGraph.Edge edge : graph.getNeighbors(current)) {
                UniversityStudent neighbor = edge.getTarget();
                double newDistance = distances.get(current) + edge.getWeight();

                // Update the shortest path to this neighbor if it's better
                if (newDistance < distances.get(neighbor)) {
                    distances.put(neighbor, newDistance);
                    parents.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        // No path found
        return new ArrayList<>();
    }

    // Helper method to reconstruct the path from the parent map
    private List<UniversityStudent> reconstructPath(Map<UniversityStudent, UniversityStudent> parents, UniversityStudent target) {
        List<UniversityStudent> path = new ArrayList<>();
        for (UniversityStudent at = target; at != null; at = parents.get(at)) {
            path.add(0, at); // Prepend to the path
        }
        return path;
    }
}
