import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Producers implements Runnable{
    private final MessageQueue queue;

    public Producers(MessageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        for(int i=0;i<5;i++) {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timestamp = now.format(formatter);
            queue.put(timestamp);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
