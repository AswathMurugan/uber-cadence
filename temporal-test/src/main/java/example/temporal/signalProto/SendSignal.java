package example.temporal.signalProto;

import example.temporal.signalProto.processor.ProcessorWorkFlow;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;

import java.util.HashMap;
import java.util.List;

public class SendSignal {
    public static void main(String[] args) throws InterruptedException {
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);
        // Create a new stub using the workflowId.
        // This is to demonstrate that to send a signal only the workflowId is required.
        ProcessorWorkFlow workflowById = client.newWorkflowStub(ProcessorWorkFlow.class, "WORKFLOW_SIGNAL_PROTO_ONE");
        HashMap<String ,String> tmp1 = new HashMap<>();
        tmp1.put("id","1");
        tmp1.put("input","10");

        workflowById.waitForMessage(tmp1);
        workflowById.exit();
        Thread.sleep(20000);
        //System.out.println(workflowById.processorReturnResult("1"));
        String greetings = workflowById.execute();
        System.out.println(greetings);

    }
}
