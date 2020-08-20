package example.temporal.signalProto.senderWorkFlow.senderD;

import example.temporal.signalProto.SignalProtoWorker;
import io.temporal.client.WorkflowStub;

import java.util.HashMap;
import java.util.Optional;

public class DactivityImpl implements Dactivity{

    private HashMap<String, String> messageBuilder(String workflowId, String input, String runId) {
        HashMap<String ,String> data = new HashMap<>();
        data.put("id", workflowId);
        data.put("input",input);
        data.put("runId", runId);
        data.put("signal","senderDResult");
        return data;
    }

    @Override
    public void signalActivity(String signalWorkFlowId, String workflowId, String runId, String input) {
        WorkflowStub processorWorkFlow = SignalProtoWorker.client
                .newUntypedWorkflowStub(signalWorkFlowId, Optional.empty(),Optional.empty());
        HashMap<String, String> data = messageBuilder(workflowId,input,runId);
        processorWorkFlow.signal("waitForMessage", data);
    }
}
