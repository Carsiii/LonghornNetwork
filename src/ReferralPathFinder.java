import java.util.*;

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
        // Method signature only
        return new ArrayList<>();
    }
}
