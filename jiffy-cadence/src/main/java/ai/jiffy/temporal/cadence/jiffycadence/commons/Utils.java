package ai.jiffy.temporal.cadence.jiffycadence.commons;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.temporal.client.ActivityCompletionClient;
import io.temporal.workflow.Promise;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class Utils {

    private static Logger logger = Workflow.getLogger(Utils.class);

    public static ObjectMapper mapper = new ObjectMapper();



    public static CompletableFuture<ResponseEntity<String>> asyncWebClientGet(String url, String val) {
        logger.info("Welcome to asyn web client mono response ebtry");
        CompletableFuture<ResponseEntity<String>> completableFuture;
        String formatURL = String.format(url, val);

        WebClient webClient = WebClient
                .builder()
                .build();

        Mono<ResponseEntity<String>> responseEntityMono = webClient
                .get()
                .uri(formatURL)
                .retrieve()
                .toEntity(String.class);

        logger.info("External call to rest service ....{}",formatURL);
        completableFuture = responseEntityMono.toFuture();
        return completableFuture;
    }

    public static HttpResponse<String> getStringHttpResponse(String url, String val) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String formatURL = String.format(url, val);
        logger.info("Thread..{} Calling External API get request....{}",Thread.currentThread().getName(),
                formatURL);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(formatURL))
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    public static void asynTemporalGetStringHttpResponse(String url, String val, byte[] taskToken, ActivityCompletionClient completionClient) throws IOException, InterruptedException {

        CompletableFuture<ResponseEntity<String>> completableFuture;
        String formatURL = String.format(url, val);

        WebClient webClient = WebClient
                .builder()
                .build();

        Mono<ResponseEntity<String>> responseEntityMono = webClient
                .get()
                .uri(formatURL)
                .retrieve()
                .toEntity(String.class);
        completableFuture = responseEntityMono.toFuture();
        completableFuture.whenCompleteAsync((response, err)->{
            completionClient.complete(taskToken,response.getBody());
        });

        /*HttpClient client = HttpClient.newHttpClient();
        String formatURL = String.format(url, val);
        logger.info("Thread..{} Calling External API get request....{}",Thread.currentThread().getName(),
                formatURL);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(formatURL))
                .build();

        HttpResponse<String> send = client.send(request, HttpResponse.BodyHandlers.ofString());
        completionClient.complete(taskToken,send.body());*/
    }


    public static Promise<ResponseEntity<String>> promiseGetStringHttpResponse(String url, String val) throws IOException, InterruptedException {
        logger.info("Welcome to asyn web client mono response ebtry");
        Promise<ResponseEntity<String>> completablePromise;
        String formatURL = String.format(url, val);

        WebClient webClient = WebClient
                .builder()
                .build();

        Mono<ResponseEntity<String>> responseEntityMono = webClient
                .get()
                .uri(formatURL)
                .retrieve()
                .toEntity(String.class);

        logger.info("Thread..{} Calling External API get request....{}",Thread.currentThread().getName(),
                formatURL);
        completablePromise = (Promise<ResponseEntity<String>>) responseEntityMono.toFuture();

        return completablePromise;
    }

}
