package example.temporal.fileprocessor;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;

import static example.temporal.fileprocessor.FileProcessorWorker.TASK_QUEUE;


public class FileProcessorClient {
    public static void main(String[] args) {
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);
        WorkflowOptions workflowOptions =
                WorkflowOptions.newBuilder().setTaskQueue(TASK_QUEUE).build();

        FileProcessorWorkFlow workFlow = client.newWorkflowStub(
                FileProcessorWorkFlow.class,workflowOptions
        );
        workFlow.fileProcessorRun("/home/aswath.murugan/Documents/ques","http://127.0.0.1:8175/uploadFile");
        System.exit(0);
    }
}
