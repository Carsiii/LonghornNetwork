import java.util.*;
import java.util.stream.Collectors;

/**
 * creates groups (pods) of students
 * based on their connection strengths within a StudentGraph.
 */
public class PodFormation {

    private StudentGraph graph;
    private List<List<UniversityStudent>> pods;

    /**
     * Constructs a PodFormation instance using the provided StudentGraph.
     * @param graph the StudentGraph representing relationships and connection strengths
     *              between students
     */
    public PodFormation(StudentGraph graph) {
        // Constructor
        this.graph = graph;
        this.pods = new ArrayList<>();
    }

    public List<List<UniversityStudent>> getPods() {
        return pods;
    }

    /**
     * Forms pods of students based on connection strengths, ensuring that each pod has
     * no more than the specified size.
     * The method applies Prim’s algorithm on the graph to construct Minimum Spanning Trees (MSTs)
     * for each connected component, which are then divided into pods of the desired size.
     * @param podSize the maximum number of students allowed in a single pod
     */
    public void formPods(int podSize) {
        if (graph.getAllNodes().isEmpty()) {
            System.out.println("No students to assign to pods.");
            return;
        }
        Set<UniversityStudent> visited = new HashSet<>();
        for (UniversityStudent student : graph.getAllNodes()) {
            if (!visited.contains(student)) {
                List<UniversityStudent> mst = buildMST(student, visited);
                divideIntoPods(mst, podSize);
            }
        }
        printPodAssignmentss();
    }

    private List<UniversityStudent> buildMST(UniversityStudent start, Set<UniversityStudent> visited) {
        List<UniversityStudent> mst = new ArrayList<>();
        PriorityQueue<StudentGraph.Edge> pq = new PriorityQueue<>(Comparator.comparingInt(StudentGraph.Edge::getWeight));
        visited.add(start);
        mst.add(start);

        pq.addAll(graph.getNeighbors(start));

        while (!pq.isEmpty()) {
            StudentGraph.Edge edge = pq.poll();
            UniversityStudent target = edge.getTarget();
            if (!visited.contains(target)) {
                visited.add(target);
                mst.add(target);
                pq.addAll(graph.getNeighbors(target));
            }
        }
        return mst;
    }

    private void divideIntoPods(List<UniversityStudent> mst, int podSize) {
        for (int i = 0; i < mst.size(); i += podSize) {
            pods.add(mst.subList(i, Math.min(i + podSize, mst.size())));
        }
    }

    private void printPodAssignments() {
        System.out.println("\nPod Assignments:");
        for (int i = 0; i < pods.size(); i++) {
            String line = "";
            System.out.print("Pod " + (i + 1) + ": ");
            for (int j = 0; j < pods.get(i).size(); j++){
                System.out.print(pods.get(i).get(j).getName() + ", ");
            }
        }
    }

    private void printPodAssignmentss() {
        System.out.println("\nPod Assignments:");
        for (int i = 0; i < pods.size(); i++) {
            // Extract student names from the current pod
            String podNames = pods.get(i).stream()
                    .map(UniversityStudent::getName) // Get the name of each student
                    .collect(Collectors.joining(", ")); // Join names with commas
            System.out.println("Pod " + (i + 1) + ": " + podNames);
        }
    }
}
