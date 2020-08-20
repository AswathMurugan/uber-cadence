package example.temporal.signalProto;

import example.temporal.signalProto.processor.ProcessorWorkFlow;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;

import static example.temporal.signalProto.SignalProtoWorker.TASK_QUEUE;

public class SignalProtoClient {

    // In a real application use a business ID like customer ID or order ID
    static String workflowId = "WORKFLOW_SIGNAL_PROTO_ONE";

    public static void main(String[] args) throws InterruptedException {
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);




        WorkflowOptions workflowOptions =
                WorkflowOptions.newBuilder().setTaskQueue(TASK_QUEUE).setWorkflowId(workflowId).build();
        ProcessorWorkFlow workflow = client.newWorkflowStub(ProcessorWorkFlow.class, workflowOptions);
        // Start workflow asynchronously to not use another thread to signal.
        WorkflowClient.start(workflow::execute);
        System.out.println("Process WorkFlow is started and waiting for signal message ");
        Thread.sleep(6000);
    }
}
