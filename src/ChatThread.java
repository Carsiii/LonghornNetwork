import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Simulates a chat interaction between two UniversityStudents
 * implements Runnable
 */
public class ChatThread implements Runnable {

    private UniversityStudent sender;
    private UniversityStudent receiver;
    private String message;
    private static final Lock chatLock = new ReentrantLock();

    /**
     * Constructs a ChatThread object to send a message from one student to another.
     * @param sender   the UniversityStudent sending the message
     * @param receiver the UniversityStudent receiving the message
     * @param message  the content of the message to be sent
     */
    public ChatThread(UniversityStudent sender, UniversityStudent receiver, String message) {
        // Constructor
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    /**
     * Executes the chat operation between the sender and receiver.
     */
    @Override
    public void run() {
        chatLock.lock();
        String senderMessage = sender.getName() + ": " + message;
        String receiverMessage = receiver.getName() + ": " + message;

        sender.addMessage(receiver.getName(), senderMessage);
        receiver.addMessage(sender.getName(), receiverMessage);

        System.out.println(sender.getName() + " sent a message to " + receiver.getName() + ": " + message);
        chatLock.unlock();
    }
}
