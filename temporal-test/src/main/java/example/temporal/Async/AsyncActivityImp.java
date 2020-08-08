package example.temporal.Async;


import io.temporal.activity.Activity;
import io.temporal.activity.ActivityExecutionContext;
import io.temporal.client.ActivityCompletionClient;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

import static example.temporal.Async.MainWorker.GET_URL;
import static example.temporal.Async.MainWorker.GET_URL_SLEEEP;

public class AsyncActivityImp implements AsyncActivity {

    private static Logger logger = Workflow.getLogger(AsyncActivityImp.class);

    private final ActivityCompletionClient completionClient;

    public AsyncActivityImp(ActivityCompletionClient completionClient) {
        this.completionClient = completionClient;
    }


    @Override
    public String asyncApiCallOne() {

        ActivityExecutionContext context = Activity.getExecutionContext();
        byte[] taskToken = context.getTaskToken();
        asyncGetStringHttpResponse(GET_URL_SLEEEP,"30",taskToken,completionClient);
        context.doNotCompleteOnReturn();
        return "ignore";
    }



    //Async rest
    //webflux
    public void asyncGetStringHttpResponse(String url, String val, byte[] taskToken, ActivityCompletionClient completionClient) {

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
