package example.temporal.loadtest;


import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import org.apache.commons.lang.RandomStringUtils;

import java.util.stream.IntStream;

public class LoadTestClientTestParallel {
    public static void main(String[] args) {
        IntStream.range(0,1).parallel().forEach(i->{
            ManagedChannel managedChannel =
                    ManagedChannelBuilder.forAddress("localhost",7233)
                            .usePlaintext()
                            .build();
            // Start a worker that hosts the workflow implementation.
            WorkflowServiceStubs service = WorkflowServiceStubs.newInstance(WorkflowServiceStubsOptions.newBuilder()
                    .setChannel(managedChannel)
                    .build());
            WorkflowClient client = WorkflowClient.newInstance(service);
            String workflowId = RandomStringUtils.randomAlphabetic(10) +"-"+i;
            LoadTestWorkFlow workflow =
                    client.newWorkflowStub(
                            LoadTestWorkFlow.class, WorkflowOptions.newBuilder()
                                    .setWorkflowId(workflowId)
                                    .setTaskQueue(LoadTestWorker.TASK_QUEUE).build());
            WorkflowExecution workflowExecution = WorkflowClient.start(workflow::runWorkFlow);
            System.out.println("Started process file workflow with workflowId=\"" + workflowExecution.getWorkflowId()
                    + "\" and runId=\"" + workflowExecution.getRunId() + "\"");
        });
    }
}
