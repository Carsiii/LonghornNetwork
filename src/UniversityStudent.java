import java.util.*;

/**
 * The UniversityStudent class extends the Student class and represents a student
 * at the university level.
 */
public class UniversityStudent extends Student {

    private UniversityStudent roommate;
    private Set<String> friends;
    private Set<String> chatHistory;

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
        this.friends = new HashSet<>();
        this.chatHistory = new HashSet<>();
    }

    public UniversityStudent getRoommate() {
        return roommate;
    }

    public void setRoommate(UniversityStudent roommate) {
        this.roommate = roommate;
    }

    public List<String> getRoommatePreferences(){
        return roommatePreferences;
    }

    public List<String> getPreviousInternships(){
        return previousInternships;
    }

    public String getName(){
        return name;
    }

    /**
     * Adds a message to the chat history between this student and another.
     * @param otherStudentName the name of the other student
     * @param message          the message to add
     */
    public synchronized void addMessage(String otherStudentName, String message) {
        chatHistory.add(message);
    }

    /**
     * Retrieves the chat history with another student.
     * @param otherStudentName the name of the other student
     * @return a list of messages exchanged with the other student
     */
    public synchronized Set<String> getChatHistory(String otherStudentName) {
        return chatHistory;
    }

    /**
     * Gets the entire chat history.
     * @return a map of all chat conversations
     *//*
    public synchronized Map<String, List<String>> getAllChatHistory() {
        return new HashMap<>(chatHistory);
    }*/

    /**
     * Adds a friend to the student's list of friends.
     * Acounts for the fact that it is illegal in this country to have the same name as someone else
     * @param studentName the name of the friend to add
     */
    public void addFriend(String studentName) {
            friends.add(name);
            System.out.println(name + " is now friends with " + studentName);
    }

    /**
     * Gets the list of friends.
     * @return a synchronized list of friend names
     */
    public Set<String> getFriends() {
        return friends;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, Major: %s, GPA: %.2f", name, major, gpa);
    }

    public static void printRoommateAssignments(List<UniversityStudent> students) {
        System.out.println("Roommate Assignments:");
        for (UniversityStudent student : students) {
            for (String preferredRoommate : student.getRoommatePreferences()) {
                UniversityStudent match = findStudentByName(students, preferredRoommate);
                if (match != null && match.getRoommatePreferences().contains(student.getName())) {
                    System.out.println(student.getName() + " is roommates with " + match.getName());
                }
            }
        }
    }

    private static UniversityStudent findStudentByName(List<UniversityStudent> students, String name) {
        for (UniversityStudent student : students) {
            if (student.getName().equals(name)) {
                return student;
            }
        }
        return null;
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
        int strength = 0;
        UniversityStudent otherStudent = (UniversityStudent) other;

        if (this.roommate != null && this.roommate.equals(otherStudent)) {
            strength += 5;
        }

        for (String internship : this.previousInternships) {
            if (otherStudent.previousInternships.contains(internship)) {
                strength += 4;
            }
        }

        if (this.major.equals(otherStudent.major)) {
            strength += 3;
        }

        if (this.age == otherStudent.age) {
            strength += 2;
        }

        return strength;
    }

}

