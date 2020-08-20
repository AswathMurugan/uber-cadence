package example.temporal.fileprocessor;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface FileProcessorWorkFlow {

    @WorkflowMethod
    void fileProcessorRun(String filePath, String uploadUrl);
}
