package example.temporal.signalProto.senderWorkFlow.senderC;

import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface CWorkFlow {

    @WorkflowMethod
    String cExecute(String id,String sec);

    @SignalMethod
    void senderCResult(String result);
}
