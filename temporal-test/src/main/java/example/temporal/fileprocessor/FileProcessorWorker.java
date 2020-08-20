package example.temporal.fileprocessor;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

public class FileProcessorWorker {

    public static final String TASK_QUEUE = "FILE_PROCESSOR";

    public static WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
    public static WorkflowClient client = WorkflowClient.newInstance(service);

    public static void main(String[] args) {
        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker(TASK_QUEUE);
        worker.registerWorkflowImplementationTypes(FileProcessorWorkFlowImpl.class);
        worker.registerActivitiesImplementations(new FileProcessorActivityImpl());
        factory.start();
        System.out.println("Started the worker task queue name: " + TASK_QUEUE);
    }
}
