import java.util.concurrent.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // callable and future
        ExecutorService service = Executors.newFixedThreadPool(1);
        try {
        Future<Integer> future = service.submit(new Task(5));
        System.out.println(future.get());
        System.out.println("Blocking");
        } finally {
            service.shutdown();
        }
    }

    static class Task implements Callable<Integer> {
        private final int n;

        Task(int n) {
            this.n = n;
        }
        @Override
        public Integer call() throws Exception {
            int sum = 0;
            for(int i=1;i<=n;i++) sum += i;
            return sum;
        }
    }
}