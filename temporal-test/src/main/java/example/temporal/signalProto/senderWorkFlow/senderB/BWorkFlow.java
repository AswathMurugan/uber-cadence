package example.temporal.signalProto.senderWorkFlow.senderB;

import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface BWorkFlow {

    @WorkflowMethod
    void bExecute(String signalWorkFlowId, int id);

    @SignalMethod
    void senderBResult(String result);
}
