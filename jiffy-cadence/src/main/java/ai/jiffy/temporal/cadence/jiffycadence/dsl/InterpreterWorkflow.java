package ai.jiffy.temporal.cadence.jiffycadence.dsl;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface InterpreterWorkflow {

    @WorkflowMethod
    String execute(String type, String input);
}
