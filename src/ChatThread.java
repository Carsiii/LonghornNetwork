/**
 * Simulates a chat interaction between two UniversityStudents
 * implements Runnable
 */
public class ChatThread implements Runnable {

    private UniversityStudent sender;
    private UniversityStudent receiver;
    private String message;

    /**
     * Constructs a ChatThread object to send a message from one student to another.
     * @param sender   the UniversityStudent sending the message
     * @param receiver the UniversityStudent receiving the message
     * @param message  the content of the message to be sent
     */
    public ChatThread(UniversityStudent sender, UniversityStudent receiver, String message) {
        // Constructor
    }

    /**
     * Executes the chat operation between the sender and receiver.
     */
    @Override
    public void run() {
        // Method signature only
    }
}
