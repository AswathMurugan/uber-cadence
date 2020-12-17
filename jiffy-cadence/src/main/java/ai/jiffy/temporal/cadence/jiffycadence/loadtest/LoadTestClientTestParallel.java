package ai.jiffy.temporal.cadence.jiffycadence.loadtest;


import ai.jiffy.temporal.cadence.jiffycadence.commons.StringConstant;
import ai.jiffy.temporal.cadence.jiffycadence.config.CadenceWorker;
import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.apache.commons.lang.RandomStringUtils;

import java.util.stream.IntStream;

public class LoadTestClientTestParallel {
    public static void main(String[] args) {
        IntStream.range(0,2000).parallel().forEach(i->{
            String workflowId = RandomStringUtils.randomAlphabetic(10) +"-"+i;
            LoadTestWorkFlow workflow =
                    CadenceWorker.client.newWorkflowStub(
                            LoadTestWorkFlow.class, WorkflowOptions.newBuilder()
                                    .setWorkflowId(workflowId)
                                    .setTaskQueue(StringConstant.TASK_QUEUE).build());
            WorkflowExecution workflowExecution = WorkflowClient.start(workflow::runWorkFlow);
            System.out.println("Started process file workflow with workflowId=\"" + workflowExecution.getWorkflowId()
                    + "\" and runId=\"" + workflowExecution.getRunId() + "\"");
        });
    }
}
