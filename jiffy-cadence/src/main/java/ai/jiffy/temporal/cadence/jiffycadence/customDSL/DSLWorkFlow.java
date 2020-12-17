package ai.jiffy.temporal.cadence.jiffycadence.customDSL;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface DSLWorkFlow {

    @WorkflowMethod
    String execute(String state);

}
