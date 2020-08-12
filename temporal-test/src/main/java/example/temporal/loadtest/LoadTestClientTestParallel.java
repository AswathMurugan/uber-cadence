package example.temporal.loadtest;


import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.apache.commons.lang.RandomStringUtils;

import java.util.stream.IntStream;

public class LoadTestClientTestParallel {
    public static void main(String[] args) {
        IntStream.range(0,100).parallel().forEach(i->{
            String workflowId = RandomStringUtils.randomAlphabetic(10) +"-"+i;
            LoadTestWorkFlow workflow =
                    LoadTestWorker.client.newWorkflowStub(
                            LoadTestWorkFlow.class, WorkflowOptions.newBuilder()
                                    .setWorkflowId(workflowId)
                                    .setTaskQueue(LoadTestWorker.TASK_QUEUE).build());
            WorkflowExecution workflowExecution = WorkflowClient.start(workflow::runWorkFlow);
            System.out.println("Started process file workflow with workflowId=\"" + workflowExecution.getWorkflowId()
                    + "\" and runId=\"" + workflowExecution.getRunId() + "\"");
        });
    }
}
