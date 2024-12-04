import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * the entry point for the Longhorn Network application.
 * orchestrates the parsing of student data, roommate matching, pod formation, and referral pathfinding
 * to simulate the network of university students.
 */
public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide the input file name as a command-line argument.");
            return;
        }
        String inputFile = args[0];
        try {
            List<UniversityStudent> students = DataParser.parseStudents(inputFile);

            // Roommate matching
            GaleShapley.assignRoommates(students);

            // Pod formation
            StudentGraph graph = new StudentGraph(students);
            PodFormation podFormation = new PodFormation(graph);
            podFormation.formPods(4);

            // Referral path finding
            ReferralPathFinder pathFinder = new ReferralPathFinder(graph);
            System.out.println("\nReferral Path:");
            List<UniversityStudent> referralPath = pathFinder.findReferralPath(students.get(1), "Google");
            pathFinder.printReferralPath(students.get(1), "Google");

            List<UniversityStudent> referralPath2 = pathFinder.findReferralPath(students.get(0), "Microsoft");
            pathFinder.printReferralPath(students.get(0), "Microsoft");

            ExecutorService executor = Executors.newFixedThreadPool(3);
            executor.submit(new FriendRequestThread(students.get(0), students.get(1)));
            executor.submit(new ChatThread(students.get(0), students.get(1), "hi"));
            executor.submit(new ChatThread(students.get(1), students.get(0), "bye"));
            executor.shutdown();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
