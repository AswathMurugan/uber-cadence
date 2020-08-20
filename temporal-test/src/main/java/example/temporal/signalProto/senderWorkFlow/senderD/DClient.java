package example.temporal.signalProto.senderWorkFlow.senderD;

import example.temporal.signalProto.SignalProtoWorker;
import example.temporal.signalProto.senderWorkFlow.senderB.BWorkFlow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;

public class DClient {

    public static void main(String[] args) {
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);

        DWorkflow workflow =
                client.newWorkflowStub(
                        DWorkflow.class, WorkflowOptions.newBuilder()
                                .setTaskQueue(SignalProtoWorker.TASK_QUEUE)
                                .setWorkflowId("SENDER_D")
                                //.setWorkflowTaskTimeout(Duration.ofMinutes(5))
                                .build());
        workflow.dExecute("WORKFLOW_SIGNAL_PROTO_A",1);
        System.exit(0);
    }
}
