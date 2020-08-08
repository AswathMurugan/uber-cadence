package example.temporal.Async;

import io.temporal.activity.*;
import io.temporal.client.ActivityCompletionClient;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import io.temporal.workflow.*;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class MainWorker {

    public static String TASK_QUEUE = "HelloActiveAsync";

    //Simple spring boot rest it will same input
    public static String GET_URL = "http://localhost:9012/ca/%s";
    public static String GET_URL_SLEEEP = "http://localhost:9012/ca/sleep/%s";

    // Start a worker that hosts the workflow implementation.
    public static WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
    public static WorkflowClient client = WorkflowClient.newInstance(service);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker(TASK_QUEUE);


        worker.registerWorkflowImplementationTypes(AsyncActivityWorkFlowImpl.class);
        ActivityCompletionClient completionClient = client.newActivityCompletionClient();
        worker.registerActivitiesImplementations(new AsyncActivityImp(completionClient));
        factory.start();
        System.out.println("Started Worker");




    }


}
