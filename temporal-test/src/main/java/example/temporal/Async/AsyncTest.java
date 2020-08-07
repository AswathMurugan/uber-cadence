package example.temporal.Async;

import io.temporal.activity.*;
import io.temporal.client.ActivityCompletionClient;
import io.temporal.client.WorkflowClient;
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

public class AsyncTest {

    public static String TASK_QUEUE = "HelloActiveAsync";

    //Simple spring boot rest it will same input
    public static String GET_URL = "http://localhost:9012/ca/%s";
    
    @ActivityInterface
    public interface AsyncActivity {

        @ActivityMethod
        String asyncApiCallOne();
    }

    @WorkflowInterface
    public interface AsyncActivityWorkFlow {

        @WorkflowMethod
        String execute(String input);
    }


    public class AsyncActivityWorkFlowImpl implements AsyncActivityWorkFlow {

        private final AsyncActivity activity =
                Workflow.newActivityStub(
                        AsyncActivity.class,
                        ActivityOptions.newBuilder()
                                .setScheduleToCloseTimeout(Duration.ofMinutes(15)).build()
                );


        public String execute(String input) {
            Promise<String> asyncApiCallOne = Async.function(activity::asyncApiCallOne);
            asyncApiCallOne.thenApply(resp -> {
                System.out.println("Commplete workflow {}" + resp);
                return resp;
            });


            return asyncApiCallOne.get();
        }
    }

    public static class AsyncActivityImp implements AsyncActivity {

        private final ActivityCompletionClient completionClient;

        public AsyncActivityImp(ActivityCompletionClient completionClient) {
            this.completionClient = completionClient;
        }


        @Override
        public String asyncApiCallOne() {
            //Calling external api using Spring web flux in Async
            //"asyncGetStringHttpResponse"

            ActivityExecutionContext context = Activity.getExecutionContext();
            byte[] taskToken = context.getTaskToken();

            try {
                asyncGetStringHttpResponse(GET_URL, "one", taskToken, completionClient);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            context.doNotCompleteOnReturn();
            return "igoner";
        }

        //Async rest
        //webflux
        public void asyncGetStringHttpResponse(String url, String val, byte[] taskToken, ActivityCompletionClient completionClient) throws IOException, InterruptedException {

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

    // Start a worker that hosts the workflow implementation.
    public static WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
    public static WorkflowClient client = WorkflowClient.newInstance(service);

    public static void main(String[] args) {

        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker(TASK_QUEUE);


        worker.registerWorkflowImplementationTypes(AsyncActivityWorkFlowImpl.class);
        ActivityCompletionClient completionClient = client.newActivityCompletionClient();
        worker.registerActivitiesImplementations(new AsyncActivityImp(completionClient));
        factory.start();
        System.out.println("Started Worker");

    }
}
