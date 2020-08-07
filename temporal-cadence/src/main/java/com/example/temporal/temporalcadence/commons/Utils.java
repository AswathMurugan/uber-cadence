package com.example.temporal.temporalcadence.commons;

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

    }

}
