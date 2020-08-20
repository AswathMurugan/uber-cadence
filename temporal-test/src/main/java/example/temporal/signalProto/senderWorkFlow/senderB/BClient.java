package example.temporal.signalProto.senderWorkFlow.senderB;

import example.temporal.signalProto.SignalProtoWorker;
import example.temporal.signalProto.senderWorkFlow.senderA.AWorkFlow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;

import java.time.Duration;

public class BClient {

    public static void main(String[] args) {
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);

        BWorkFlow workflow =
                client.newWorkflowStub(
                        BWorkFlow.class, WorkflowOptions.newBuilder()
                                .setTaskQueue(SignalProtoWorker.TASK_QUEUE)
                                .setWorkflowId("SENDER_B")
                                //.setWorkflowTaskTimeout(Duration.ofMinutes(5))
                                .build());
        workflow.bExecute("WORKFLOW_SIGNAL_PROTO_A",1);
        System.exit(0);
    }
}
