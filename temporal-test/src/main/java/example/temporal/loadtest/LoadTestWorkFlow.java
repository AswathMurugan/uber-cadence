package example.temporal.loadtest;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface LoadTestWorkFlow {

    @WorkflowMethod
    String runWorkFlow();


}
