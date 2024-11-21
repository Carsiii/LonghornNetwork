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
        return new ArrayList<>();
    }
}
