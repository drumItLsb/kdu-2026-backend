public class ProducerConsumerDemo {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue();

        for(int i=0;i<3;i++) {
            Thread t1 = new Thread(new Producers(queue));
            Thread t2 = new Thread(new Consumer(queue));

            t1.start();
            t2.start();
        }
    }
}
