import java.util.*;

/**
 * The UniversityStudent class extends the Student class and represents a student
 * at the university level.
 */
public class UniversityStudent extends Student {
    // TODO: Constructor and additional methods to be implemented

    /**
     * constructor initializes the student's name, age, gender, year, major, GPA, roommate preferences, and
     * previous internships. These attributes are important for the student's identity and relationships within the university.
     * @param name               the name of the student
     * @param age                the age of the student
     * @param gender             the gender of the student
     * @param year               the year of study the student is in
     * @param major              the student's major
     * @param gpa                the student's GPA
     * @param roommatePreferences the list of roommate preferences for the student
     * @param previousInternships the list of previous internships the student has completed
     */
    public UniversityStudent(String name, int age, String gender, int year, String major, double gpa,
                             List<String> roommatePreferences, List<String> previousInternships) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.year = year;
        this.major = major;
        this.gpa = gpa;
        this.roommatePreferences = roommatePreferences != null ? roommatePreferences : new ArrayList<>();
        this.previousInternships = previousInternships != null ? previousInternships : new ArrayList<>();
    }

    /**
     * Calculates the connection strength between this university student and another Student.
     * The connection strength is based on several factors, such as whether the students are roommates,
     * whether they have shared internships, and their academic or social attributes.
     * @param other the Student with whom the connection strength is calculated
     * @return an integer representing the calculated connection strength between the two students
     */
    @Override
    public int calculateConnectionStrength(Student other) {
        return 0;
    }
}

