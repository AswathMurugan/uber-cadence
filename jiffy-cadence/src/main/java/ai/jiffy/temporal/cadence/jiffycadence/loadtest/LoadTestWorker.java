package ai.jiffy.temporal.cadence.jiffycadence.loadtest;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

import java.util.concurrent.ExecutionException;

public class LoadTestWorker {

    public static String TASK_QUEUE = "loadTest";



    // Start a worker that hosts the workflow implementation.
    public static WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
    public static WorkflowClient client = WorkflowClient.newInstance(service);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker(TASK_QUEUE);


        worker.registerWorkflowImplementationTypes(LoadTestWorkFlowImpl.class);
        worker.registerActivitiesImplementations(new LoadTestActivityImpl());
        factory.start();
        System.out.println("Started Worker");




    }


}
