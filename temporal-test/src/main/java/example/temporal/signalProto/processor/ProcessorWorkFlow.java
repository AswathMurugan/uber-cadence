package example.temporal.signalProto.processor;

import io.temporal.workflow.SignalMethod;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

import java.util.HashMap;

@WorkflowInterface
public interface ProcessorWorkFlow {

    @WorkflowMethod
    String execute();

    @SignalMethod
    void waitForMessage(HashMap<String, String> inputMessage);

    @SignalMethod
    void exit();
}
