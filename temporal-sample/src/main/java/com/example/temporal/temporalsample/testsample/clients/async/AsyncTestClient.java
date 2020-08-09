package com.example.temporal.temporalsample.testsample.clients.async;

import com.example.temporal.temporalsample.testsample.Async.AsyncActivityWorkFlow;
import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import static com.example.temporal.temporalsample.testsample.clients.async.MainWorker.TASK_QUEUE;

public class AsyncTestClient {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Start a workflow execution. Usually this is done from another program.
        // Uses task queue from the GreetingWorkflow @WorkflowMethod annotation.
        AsyncActivityWorkFlow workflow =
                MainWorker.client.newWorkflowStub(
                        AsyncActivityWorkFlow.class, WorkflowOptions.newBuilder().setTaskQueue(TASK_QUEUE).build());


        //CompletableFuture<String> greeting = WorkflowClient.execute(workflow::execute);

        //System.out.println(greeting.get());
        WorkflowExecution workflowExecution = WorkflowClient.start(workflow::execute);
        System.out.println("Started process file workflow with workflowId=\"" + workflowExecution.getWorkflowId()
                + "\" and runId=\"" + workflowExecution.getRunId() + "\"");

        CountDownLatch latch = new CountDownLatch(1);
        latch.await();
        System.exit(0);
    }
}
