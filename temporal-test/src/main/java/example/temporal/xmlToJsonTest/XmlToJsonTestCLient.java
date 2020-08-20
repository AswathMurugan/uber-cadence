package example.temporal.xmlToJsonTest;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import org.apache.commons.lang.RandomStringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class XmlToJsonTestCLient {

    public static void main(String[] args) throws IOException {
        ManagedChannel managedChannel =
                ManagedChannelBuilder.forAddress("localhost",7233)
                        .maxInboundMessageSize(10716573)
                        .usePlaintext()
                        .build();
        // Start a worker that hosts the workflow implementation.
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance(WorkflowServiceStubsOptions.newBuilder()
                .setChannel(managedChannel)
                .build());
        WorkflowClient client = WorkflowClient.newInstance(service);

        String workflowId = RandomStringUtils.randomAlphabetic(10);
        XJTestWorkFlow workflow =
                client.newWorkflowStub(
                        XJTestWorkFlow.class, WorkflowOptions.newBuilder()
                                .setWorkflowId(workflowId)
                                .setTaskQueue(XmlToJsonTestWorker.TASK_QUEUE).build());
        WorkflowExecution workflowExecution = WorkflowClient.start(workflow::execute,"xml");
        System.out.println("Started process file workflow with workflowId=\"" + workflowExecution.getWorkflowId()
                + "\" and runId=\"" + workflowExecution.getRunId() + "\"");
    }
}
