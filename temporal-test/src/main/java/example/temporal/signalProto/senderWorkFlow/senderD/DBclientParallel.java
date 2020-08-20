package example.temporal.signalProto.senderWorkFlow.senderD;

import example.temporal.signalProto.SignalProtoWorker;
import example.temporal.signalProto.senderWorkFlow.senderB.BWorkFlow;
import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;

import java.time.Duration;
import java.util.stream.IntStream;

public class DBclientParallel {
    public static void main(String[] args) {
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);
        IntStream.range(0,2).parallel().forEach(i->{

            DWorkflow workflow =
                    client.newWorkflowStub(
                            DWorkflow.class, WorkflowOptions.newBuilder()
                                    .setTaskQueue(SignalProtoWorker.TASK_QUEUE)
                                    .setWorkflowTaskTimeout(Duration.ofMinutes(5))
                                    .build());

            WorkflowExecution workflowExecution = WorkflowClient.start(workflow::dExecute,"WORKFLOW_SIGNAL_PROTO_A",i);
            System.out.println("Started process file workflow with workflowId=\"" + workflowExecution.getWorkflowId()
                    + "\" and runId=\"" + workflowExecution.getRunId() + "\"");
        });
    }

}
