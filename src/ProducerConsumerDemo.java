import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumerDemo {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue();
        ExecutorService producersService = Executors.newFixedThreadPool(3);
        ExecutorService consumerService = Executors.newFixedThreadPool(3);

        try {
            for(int i=0;i<3;i++) {
                producersService.submit(new Producers(queue));
                consumerService.submit(new Consumer(queue));
            }
        } finally {
            producersService.shutdown(); // this is non-blocking, doesn't wait for remaining tasks to finish
            consumerService.shutdown(); // “Stop accepting new tasks, but let already submitted tasks finish.”
            // awaitTermination --> wait until all tasks or Timeout expires or thread is interrupted
        }

    }
}
