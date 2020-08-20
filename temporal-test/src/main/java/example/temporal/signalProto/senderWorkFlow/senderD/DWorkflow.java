package example.temporal.signalProto.senderWorkFlow.senderD;

import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface DWorkflow {

    @WorkflowMethod
    void dExecute(String signalWorkFlowId, int id);

    @SignalMethod
    void senderDResult(String result);
}
