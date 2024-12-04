import java.util.*;

/**
 * represents a graph where each student is a node and the connections between
 * students are the edges. The class provides methods for adding students,
 * adding edges with weights, and performing graph traversal operations needed for algorithms like Prim's and Dijkstra's.
 */
public class StudentGraph {

    private final Map<UniversityStudent, List<Edge>> adjacencyList;
    private List<UniversityStudent> students;

    /**
     * Constructs a new StudentGraph using the provided list of students.
     * initializes the graph and adds each student as a node in the graph.<
     * @param students a list of UniversityStudent objects to add to the graph
     */
    public StudentGraph(List<UniversityStudent> students) {
        this.adjacencyList = new HashMap<>();
        for (UniversityStudent student : students) {
            addStudent(student);
        }
        buildGraph(students);
    }

    public void addStudent(UniversityStudent student) {
        adjacencyList.putIfAbsent(student, new ArrayList<>());
    }

    public void addEdge(UniversityStudent student1, UniversityStudent student2, int weight) {
        adjacencyList.get(student1).add(new Edge(student2, weight));
        adjacencyList.get(student2).add(new Edge(student1, weight));
    }

    private void buildGraph(List<UniversityStudent> students) {
        for (int i = 0; i < students.size(); i++) {
            for (int j = i + 1; j < students.size(); j++) {
                UniversityStudent student1 = students.get(i);
                UniversityStudent student2 = students.get(j);
                int weight = student1.calculateConnectionStrength(student2);
                addEdge(student1, student2, weight);
            }
        }
    }

    public List<Edge> getNeighbors(UniversityStudent student) {
        return adjacencyList.getOrDefault(student, new ArrayList<>());
    }

    public Set<UniversityStudent> getAllNodes() {
        return adjacencyList.keySet();
    }


    public static class Edge {
        private final UniversityStudent target;
        private final int weight;

        public Edge(UniversityStudent target, int weight) {
            this.target = target;
            this.weight = weight;
        }

        public UniversityStudent getTarget() {
            return target;
        }

        public int getWeight() {
            return weight;
        }
    }
}
