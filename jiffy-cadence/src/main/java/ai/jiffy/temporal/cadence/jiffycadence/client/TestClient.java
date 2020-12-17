package ai.jiffy.temporal.cadence.jiffycadence.client;

import ai.jiffy.temporal.cadence.jiffycadence.asyncTest.AsyncActivityWorkFlow;
import ai.jiffy.temporal.cadence.jiffycadence.config.CadenceWorker;
import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static ai.jiffy.temporal.cadence.jiffycadence.commons.StringConstant.GET_URL;
import static ai.jiffy.temporal.cadence.jiffycadence.commons.StringConstant.TASK_QUEUE;

public class TestClient {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Start a workflow execution. Usually this is done from another program.
        // Uses task queue from the GreetingWorkflow @WorkflowMethod annotation.
        AsyncActivityWorkFlow workflow =
                CadenceWorker.client.newWorkflowStub(
                        AsyncActivityWorkFlow.class, WorkflowOptions.newBuilder().setTaskQueue(TASK_QUEUE).build());
        // Execute a workflow asynchronously returning a future that can be used to wait for the
        // workflow
        // completion.

        CompletableFuture<String> greeting = WorkflowClient.execute(workflow::execute,"Hello");
        System.out.println(greeting.get());

        //WorkflowExecution workflowExecution = WorkflowClient.start(workflow::execute);
        //System.out.println(workflowExecution.getRunId());
        //String hello = workflow.execute();
        //System.out.println(hello);
        System.exit(0);
    }

    /*public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<ResponseEntity<String>> completableFuture;

        String formatURL = String.format(GET_URL, "Aswath");



        completableFuture = asyncWebClientGet(formatURL);
        System.out.println(completableFuture.get());
        //completableFuture.whenCompleteAsync((response, cause) -> {
        //    System.out.println(response.getBody());
        //});


    }*/


    public static CompletableFuture<ResponseEntity<String>> asyncWebClientGet(String url){
        CompletableFuture<ResponseEntity<String>> completableFuture;


        WebClient webClient=WebClient
                .builder()
                .build();

        Mono<ResponseEntity<String>> responseEntityMono = webClient
                .get()
                .uri(url)
                .retrieve()
                .toEntity(String.class);

        completableFuture = responseEntityMono.toFuture();
        return completableFuture;
    }
}
