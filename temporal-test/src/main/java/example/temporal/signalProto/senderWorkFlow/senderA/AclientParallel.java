package example.temporal.signalProto.senderWorkFlow.senderA;

import example.temporal.signalProto.SignalProtoWorker;
import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;

import java.time.Duration;
import java.util.stream.IntStream;

public class AclientParallel {
    public static void main(String[] args) {
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);
        IntStream.range(0,50).parallel().forEach(i->{
            AWorkFlow workflow =
                    client.newWorkflowStub(
                            AWorkFlow.class, WorkflowOptions.newBuilder()
                                    .setTaskQueue(SignalProtoWorker.TASK_QUEUE)
                                    .setWorkflowTaskTimeout(Duration.ofMinutes(5))
                                    .build());

            WorkflowExecution workflowExecution = WorkflowClient.start(
                    workflow::aExecute,"","10","WORKFLOW_SIGNAL_PROTO_A");
            System.out.println("Started process file workflow with workflowId=\"" + workflowExecution.getWorkflowId()
                    + "\" and runId=\"" + workflowExecution.getRunId() + "\"");
        });
    }

}
