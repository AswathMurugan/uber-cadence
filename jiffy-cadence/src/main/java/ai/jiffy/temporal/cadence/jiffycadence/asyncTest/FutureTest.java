package ai.jiffy.temporal.cadence.jiffycadence.asyncTest;

import java.util.concurrent.*;

public class FutureTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CompletableFuture<Integer> integerCompletableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;

        },executorService);
        integerCompletableFuture.whenCompleteAsync((result,error)->{
            System.out.println(result);
            latch.countDown();
        });
        latch.await();
    }
}
