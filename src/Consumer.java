public class Consumer implements Runnable{
    private final MessageQueue queue;

    public Consumer(MessageQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        for(int i=0;i<5;i++) {
            String message = queue.take();
            System.out.println(Thread.currentThread().getName()+" consuming: " + message);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
