package example.temporal.signalProto.senderWorkFlow.senderA;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface Aactivity {

    void updateResponse(String response);

    String activity_1(String signalWorkFlowId,String workflowId, String runId);
    String activity_2(String signalWorkFlowId, String workflowId, String runId);
    String activity_3(String signalWorkFlowId, String workflowId, String runId);
    String activity_4(String signalWorkFlowId, String workflowId, String runId);
    String activity_5(String signalWorkFlowId, String workflowId, String runId);
    String activity_6(String signalWorkFlowId, String workflowId, String runId);
    String activity_7(String signalWorkFlowId, String workflowId, String runId);
    String activity_8(String signalWorkFlowId, String workflowId, String runId);
    String activity_9(String signalWorkFlowId, String workflowId, String runId);
    String activity_10(String signalWorkFlowId, String workflowId, String runId);
}
