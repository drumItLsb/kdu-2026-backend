import java.util.ArrayList;
import java.util.List;

public class MessageQueue {
    private final List<String> messages;
    private int full = 0;

    MessageQueue() {
        this.messages = new ArrayList<>();
    }

    synchronized void put(String message) {
        if(full == 5) {
            try {
                wait();
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt(); // marks that this thread was interrupted
            }
        }

        messages.add(message);
        full++;
        notifyAll(); // wakes up all the threads waiting on this object
    }

    synchronized String take() {
        if(full == 0) {
            try {
                wait();
            } catch(InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        String message = messages.remove(0);
        full--;
        notifyAll(); // notify all the producers
        return message;
    }
}
