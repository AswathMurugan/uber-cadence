package example.temporal.signalProto.senderWorkFlow.senderA;

import example.temporal.loadtest.LoadTestWorkFlow;
import example.temporal.loadtest.LoadTestWorker;
import example.temporal.signalProto.SignalProtoWorker;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;

import java.time.Duration;

public class AClient {

    public static void main(String[] args) {
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);

        AWorkFlow workflow =
                client.newWorkflowStub(
                        AWorkFlow.class, WorkflowOptions.newBuilder()
                                .setTaskQueue(SignalProtoWorker.TASK_QUEUE)
                                .setWorkflowTaskTimeout(Duration.ofMinutes(5))
                                .build());
        System.out.println(workflow.aExecute("1","5", "WORKFLOW_SIGNAL_PROTO_A"));
        System.exit(0);
    }
}
