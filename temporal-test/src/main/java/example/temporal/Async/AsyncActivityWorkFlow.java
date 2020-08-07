package example.temporal.Async;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface AsyncActivityWorkFlow {

    @WorkflowMethod
    String execute();
}
