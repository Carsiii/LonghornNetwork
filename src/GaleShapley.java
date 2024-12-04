import java.util.*;

/**
 * implements the stable roommate matching algorithm
 * based on the Gale-Shapley algorithm. It pairs students as roommates by considering
 * their mutual preferences.
 */
public class GaleShapley {

    /**
     * Assigns roommates to a list of UniversityStudent objects using the Gale-Shapley algorithm.
     * The method processes each student's roommate preferences and matches students into stable pairs
     * where possible. Students with incomplete or missing preferences may remain unmatched.
     * @param students the list of UniversityStudent objects to be matched as roommates
     *                 based on their preference lists
     */
    public static void assignRoommates(List<UniversityStudent> students) {
        // List to track unpaired students
        List<UniversityStudent> unpairedStudents = new ArrayList<>(students);

        // Map of student names to their preferences for easy lookup
        Map<String, List<String>> preferenceMap = new HashMap<>();

        // Initialize preference map
        for (UniversityStudent student : students) {
            preferenceMap.put(student.getName(), new ArrayList<>(student.getRoommatePreferences()));
        }

        // Main Gale-Shapley algorithm
        while (!unpairedStudents.isEmpty()) {
            // Get the next unpaired student
            UniversityStudent proposer = unpairedStudents.remove(0);
            String proposerName = proposer.getName();
            List<String> preferences = preferenceMap.get(proposerName);

            if (preferences == null || preferences.isEmpty()) {
                // No preferences left, skip this student
                continue;
            }

            // Propose to the top preference
            String preferredRoommateName = preferences.get(0); // Get the first preference (no removal)

            // Find the preferred roommate by name
            UniversityStudent preferredRoommate = students.stream()
                    .filter(s -> s.getName().equals(preferredRoommateName))
                    .findFirst()
                    .orElse(null);

            if (preferredRoommate == null) {
                continue; // Skip if the preferred roommate is not found
            }

            if (preferredRoommate.getRoommate() == null) {
                // Preferred roommate is unpaired, form a pairing
                proposer.setRoommate(preferredRoommate);
                preferredRoommate.setRoommate(proposer);
            } else {
                // Preferred roommate is already paired
                UniversityStudent currentRoommate = students.stream()
                        .filter(s -> s.getName().equals(preferredRoommate.getRoommate()))
                        .findFirst()
                        .orElse(null);

                if (currentRoommate == null) {
                    continue; // Skip if the current roommate is not found
                }
                // Check if preferredRoommate prefers proposer over currentRoommate
                List<String> preferredRoommatePrefs = preferenceMap.get(preferredRoommate.getName());
                int proposerIndex = preferredRoommatePrefs.indexOf(proposerName);
                int currentRoommateIndex = preferredRoommatePrefs.indexOf(currentRoommate.getName());

                if (proposerIndex < currentRoommateIndex) {
                    // Preferred roommate prefers proposer, update pairing
                    proposer.setRoommate(preferredRoommate);
                    preferredRoommate.setRoommate(proposer);

                    // Unpair the current roommate
                    currentRoommate.setRoommate(null);
                    unpairedStudents.add(currentRoommate); // Add unpaired student back to the list
                } else {
                    // Proposer remains unpaired
                    unpairedStudents.add(proposer);
                }
            }
        }

        System.out.println("\nRoommate Assignments:");
        for (UniversityStudent student : students) {
            if (student.getRoommate() != null) {
                System.out.println(student.getName() + " is roommates with " + student.getRoommate().getName());
            } else {
                System.out.println(student.getName() + " has no roommate");
            }
        }
    }
}
