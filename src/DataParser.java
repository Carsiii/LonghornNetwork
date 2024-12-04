import java.io.*;
import java.util.*;

/**
 * provides utility methods for reading and parsing student data
 * from an input file. The parsed data is used to create UniversityStudent objects
 */
public class DataParser {

    /**
     * Parses student data from a specified file and creates a list of UniversityStudent objects.
     * The input file should follow the format specified in the project instructions, containing
     * information such as name, age, gender, major, GPA, roommate preferences, and previous internships.
     * Missing or malformed data will be handled gracefully.
     * @param filename the path to the input file containing student data
     * @return a list of UniversityStudent objects created from the parsed data
     * @throws IOException if an error occurs while reading the file
     */
    public static List<UniversityStudent> parseStudents(String filename) throws IOException {
        List<UniversityStudent> students = new ArrayList<>();
        String name = null;
        int age = 0;
        String gender = null;
        int year = 0;
        String major = null;
        double gpa = 0;
        List<String> roommatePreferences = new ArrayList<>();
        List<String> previousInternships = new ArrayList<>();

        String line;
        int lineNum = 0;
        boolean firstStudent = true;

        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                line = scanner.nextLine().trim();
                lineNum++;

                if (line.equals("Student:")) {
                    if (!firstStudent){
                        students.add(new UniversityStudent(name, age, gender, year, major, gpa, roommatePreferences, previousInternships));
                        name = null;
                        age = 0;
                        gender = null;
                        year = 0;
                        major = null;
                        gpa = 0;
                        roommatePreferences = new ArrayList<>();
                        previousInternships = new ArrayList<>();
                    }
                    firstStudent = false;
                }
                else if (line.startsWith("Name:")) {
                    name = line.substring(5).trim();
                } else if (line.startsWith("Age:")) {
                    age = Integer.parseInt(line.substring(4).trim());
                } else if (line.startsWith("Gender:")) {
                    gender = line.substring(7).trim();
                } else if (line.startsWith("Year:")) {
                    year = Integer.parseInt(line.substring(5).trim());
                } else if (line.startsWith("Major:")) {
                    major = line.substring(6).trim();
                } else if (line.startsWith("GPA:")) {
                    gpa = Double.parseDouble(line.substring(4).trim());
                } else if (line.startsWith("RoommatePreferences:")) {
                    String preferences = line.substring(20).trim();
                    roommatePreferences = Arrays.asList(preferences.split(",\\s*"));
                } else if (line.startsWith("PreviousInternships:")) {
                    String internships = line.substring(20).trim();
                    previousInternships = Arrays.asList(internships.split(",\\s*"));
                } else {

                }
            }
            students.add(new UniversityStudent(name, age, gender, year, major, gpa, roommatePreferences, previousInternships));

        } catch (Exception e) {
            System.err.println("invalid line: " + lineNum);
        }

        return students;
    }
}
