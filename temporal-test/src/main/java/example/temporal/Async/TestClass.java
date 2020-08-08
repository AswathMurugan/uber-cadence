package example.temporal.Async;

import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

public class TestClass {
    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(1);

        CompletableFuture<ResponseEntity<String>> completableFuture;
        String formatURL = "http://localhost:9012/ca/sleep/30";
        long time = System.currentTimeMillis();
        WebClient webClient = WebClient
                .builder()
                .build();
        System.out.println("Time to build web client 1 " + ((System.currentTimeMillis() - time)/1000));
        time = System.currentTimeMillis();
        Mono<ResponseEntity<String>> responseEntityMono = webClient
                .get()
                .uri(formatURL)
                .retrieve()
                .toEntity(String.class);
        System.out.println("Time to build mono response entity 2 " + ((System.currentTimeMillis() - time)/1000));
        time = System.currentTimeMillis();
        completableFuture = responseEntityMono.toFuture();
        System.out.println("Time to future 3 " + ((System.currentTimeMillis() - time)/1000));
        long time1 = System.currentTimeMillis();
        completableFuture.whenCompleteAsync((response, err)->{
            System.out.println("Time to Async 4 " + ((System.currentTimeMillis() - time1)/1000));
            countDownLatch.countDown();
        });
        time = System.currentTimeMillis();
        System.out.println("Time to Async 5 " + ((System.currentTimeMillis() - time1)/1000));
        countDownLatch.await();
        System.out.println("Time to Async 6 " + ((System.currentTimeMillis() - time)/1000));
    }



}
