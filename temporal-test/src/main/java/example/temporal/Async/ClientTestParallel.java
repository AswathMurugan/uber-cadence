package example.temporal.Async;

import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;

import java.util.stream.IntStream;

public class ClientTestParallel {
    public static void main(String[] args) {
        IntStream.range(0,3000).parallel().forEach(i->{
            AsyncActivityWorkFlow workflow =
                    MainWorker.client.newWorkflowStub(
                            AsyncActivityWorkFlow.class, WorkflowOptions.newBuilder().setTaskQueue(MainWorker.TASK_QUEUE).build());
            WorkflowExecution workflowExecution = WorkflowClient.start(workflow::execute);
            System.out.println("Started process file workflow with workflowId=\"" + workflowExecution.getWorkflowId()
                    + "\" and runId=\"" + workflowExecution.getRunId() + "\"");
        });
    }
}
