package example.temporal.Async;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AsyncTestClient {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Start a workflow execution. Usually this is done from another program.
        // Uses task queue from the GreetingWorkflow @WorkflowMethod annotation.
        AsyncActivityWorkFlow workflow =
                MainWorker.client.newWorkflowStub(
                        AsyncActivityWorkFlow.class, WorkflowOptions.newBuilder().setTaskQueue(MainWorker.TASK_QUEUE).build());


        CompletableFuture<String> greeting = WorkflowClient.execute(workflow::execute);

        System.out.println(greeting.get());
        System.exit(0);
    }
}
