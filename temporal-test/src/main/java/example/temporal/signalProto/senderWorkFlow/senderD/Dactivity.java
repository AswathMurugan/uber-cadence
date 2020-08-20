package example.temporal.signalProto.senderWorkFlow.senderD;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface Dactivity {

    void signalActivity(String signalWorkFlowId,String workflowId, String runId, String input);
}
