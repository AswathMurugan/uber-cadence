package ai.jiffy.temporal.cadence.jiffycadence.asyncTest;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface AsyncActivityWorkFlow {

    @WorkflowMethod
    String execute(String input);
}
