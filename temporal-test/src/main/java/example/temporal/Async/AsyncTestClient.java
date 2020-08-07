package example.temporal.Async;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.workflow.Promise;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import static example.temporal.Async.AsyncTest.TASK_QUEUE;

public class AsyncTestClient {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Start a workflow execution. Usually this is done from another program.
        // Uses task queue from the GreetingWorkflow @WorkflowMethod annotation.
        AsyncTest.AsyncActivityWorkFlow workflow =
                AsyncTest.client.newWorkflowStub(
                        AsyncTest.AsyncActivityWorkFlow.class, WorkflowOptions.newBuilder().setTaskQueue(TASK_QUEUE).build());


        CompletableFuture<String> greeting = WorkflowClient.execute(workflow::execute,"hello");

        System.out.println(greeting.get());
        System.exit(0);
    }
}
