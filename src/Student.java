import java.util.*;

/**
 * The Student class represents a general student with attributes such as name, age,
 * gender, year, major, GPA, roommate preferences, and previous internships. This class serves as
 * the base class for different types of students.
 * It provides an abstract method for calculating connection strength with another student.
 */
public abstract class Student {
    /** The name of the student. */
    protected String name;

    /** The age of the student. */
    protected int age;

    /** The gender of the student. */
    protected String gender;

    /** The year of study the student is in */
    protected int year;

    /** The major of the student */
    protected String major;

    /** The GPA of the student. */
    protected double gpa;

    /** The list of roommate preferences for the student. */
    protected List<String> roommatePreferences;

    /** The list of previous internships the student has completed. */
    protected List<String> previousInternships;

    /**
     * Calculates the connection strength between this student and another Student.
     * The connection strength is determined based on various factors such as roommate status, shared
     * internships, major, gender, and other relevant attributes.
     * @param other the Student with whom the connection strength is calculated
     * @return an integer representing the connection strength between the two students
     */
    public abstract int calculateConnectionStrength(Student other);
}
