package ai.jiffy.temporal.cadence.jiffycadence.client;

import ai.jiffy.temporal.cadence.jiffycadence.asyncTest.AsyncActivityWorkFlow;
import ai.jiffy.temporal.cadence.jiffycadence.asyncTest.AsyncActivityWorkFlowImpl;
import ai.jiffy.temporal.cadence.jiffycadence.config.CadenceWorker;
import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.workflow.Promise;
import io.temporal.workflow.Workflow;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

import static ai.jiffy.temporal.cadence.jiffycadence.commons.StringConstant.TASK_QUEUE;

public class AsyncTestCLient {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // Start a workflow execution. Usually this is done from another program.
        // Uses task queue from the GreetingWorkflow @WorkflowMethod annotation.
        AsyncActivityWorkFlow workflow =
                CadenceWorker.client.newWorkflowStub(
                        AsyncActivityWorkFlow.class, WorkflowOptions.newBuilder().setTaskQueue(TASK_QUEUE).build());


        WorkflowExecution workflowExecution = WorkflowClient.start(workflow::execute,"hello");
        System.out.println("Started process file workflow with workflowId=\"" + workflowExecution.getWorkflowId()
                + "\" and runId=\"" + workflowExecution.getRunId() + "\"");

        CountDownLatch latch = new CountDownLatch(1);
        //System.out.println(greeting);
        //String hello = workflow.execute();
        //System.out.println(hello);

        latch.await();
        System.exit(0);

    }


}
