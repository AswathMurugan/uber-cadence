package example.temporal.signalProto.senderWorkFlow.senderC;

import example.temporal.signalProto.SignalProtoWorker;
import example.temporal.signalProto.senderWorkFlow.senderA.AWorkFlow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;

import java.time.Duration;

public class CClient {

    public static void main(String[] args) {
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);

        CWorkFlow workflow =
                client.newWorkflowStub(
                        CWorkFlow.class, WorkflowOptions.newBuilder()
                                .setTaskQueue(SignalProtoWorker.TASK_QUEUE)
                                .setWorkflowId("SENDER_C")
                                .setWorkflowTaskTimeout(Duration.ofMinutes(5))
                                .build());
        System.out.println(workflow.cExecute("3","40"));
        System.exit(0);
    }
}
