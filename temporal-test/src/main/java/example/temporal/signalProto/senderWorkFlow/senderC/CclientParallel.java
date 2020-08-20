package example.temporal.signalProto.senderWorkFlow.senderC;

import example.temporal.signalProto.SignalProtoWorker;
import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;

import java.time.Duration;
import java.util.stream.IntStream;

public class CclientParallel {
    public static void main(String[] args) {
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);
        IntStream.range(0,500).parallel().forEach(i->{

            CWorkFlow workflow =
                    client.newWorkflowStub(
                            CWorkFlow.class, WorkflowOptions.newBuilder()
                                    .setTaskQueue(SignalProtoWorker.TASK_QUEUE)
                                    .setWorkflowTaskTimeout(Duration.ofMinutes(5))
                                    .build());

            WorkflowExecution workflowExecution = WorkflowClient.start(workflow::cExecute,"","20","WORKFLOW_SIGNAL_PROTO_C");
            System.out.println("Started process file workflow with workflowId=\"" + workflowExecution.getWorkflowId()
                    + "\" and runId=\"" + workflowExecution.getRunId() + "\"");
        });
    }

}
