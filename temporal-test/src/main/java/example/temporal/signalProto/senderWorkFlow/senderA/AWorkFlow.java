package example.temporal.signalProto.senderWorkFlow.senderA;

import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface AWorkFlow {

    @WorkflowMethod
    String aExecute(String id,String sec);

    @SignalMethod
    void senderAResult(String result);
}
