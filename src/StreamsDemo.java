import java.util.stream.IntStream;

public class StreamsDemo {
    public static void main(String[] args) {
        // parallel streams
        long start = 0, end = 0;

        start = System.currentTimeMillis();
        int sum = IntStream.range(1,1_000_000).sum();
        end = System.currentTimeMillis();

        System.out.println("Time taken by sequential stream: "+ (end - start)); // simple happens faster

        start = System.currentTimeMillis();
        sum = IntStream.range(1,1_000_000).parallel().sum();
        end = System.currentTimeMillis();

        System.out.println("Time taken by parallel stream: "+ (end - start)); // take more time this is a overkill
    }
}
