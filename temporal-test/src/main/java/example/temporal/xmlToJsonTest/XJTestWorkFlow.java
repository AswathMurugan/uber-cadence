package example.temporal.xmlToJsonTest;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface XJTestWorkFlow {

    @WorkflowMethod
    String execute(String xmlData);
}
