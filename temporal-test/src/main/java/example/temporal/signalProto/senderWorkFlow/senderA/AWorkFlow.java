package example.temporal.signalProto.senderWorkFlow.senderA;

import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

import java.util.List;

@WorkflowInterface
public interface AWorkFlow {

    @WorkflowMethod
    List<String> aExecute(String id,String sec, String workflowName);

    @SignalMethod
    void senderAResult(String result);
}
