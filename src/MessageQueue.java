import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MessageQueue {

    private final List<String> messages = new ArrayList<>();
    private final int CAPACITY = 5;
    private int full = 0;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    void put(String message) {
        lock.lock();
        try {
            while (full == CAPACITY) {   // use while, not if
                try {
                    notFull.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }

            messages.add(message);
            full++;

            notEmpty.signalAll();        // wake consumers
        } finally {
            lock.unlock();
        }
    }

    String take() {
        lock.lock();
        try {
            while (full == 0) {          // use while, not if
                try {
                    notEmpty.await();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return null;
                }
            }

            String message = messages.remove(0);
            full--;

            notFull.signalAll();         // wake producers
            return message;
        } finally {
            lock.unlock();
        }
    }
}
