import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * simulates the process of sending a friend request
 * from one UniversityStudent to another in a multithreaded environment.
 * implements Runnable
 */
public class FriendRequestThread implements Runnable {

    private final UniversityStudent sender;
    private final UniversityStudent receiver;
    private static final Lock friendLock = new ReentrantLock();
    /**
     * Constructs a FriendRequestThread to initiate a friend request from one student to another.
     * @param sender   the UniversityStudent sending the friend request
     * @param receiver the UniversityStudent receiving the friend request
     */
    public FriendRequestThread(UniversityStudent sender, UniversityStudent receiver) {
        this.sender = sender;
        this.receiver = receiver;
    }

    /**
     * Executes the friend request operation between the sender and receiver.
     */
    @Override
    public void run() {
        // Method signature only
        friendLock.lock();
        receiver.addFriend(sender.getName());
        sender.addFriend(receiver.getName());
        friendLock.unlock();


    }
}
