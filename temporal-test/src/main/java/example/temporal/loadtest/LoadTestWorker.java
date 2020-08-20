package example.temporal.loadtest;

import example.temporal.Async.AsyncActivityImp;
import example.temporal.Async.AsyncActivityWorkFlowImpl;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.temporal.client.ActivityCompletionClient;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

import java.time.Duration;
import java.util.concurrent.ExecutionException;

public class LoadTestWorker {

    public static String TASK_QUEUE = "loadTest";


    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ManagedChannel managedChannel =
                ManagedChannelBuilder.forAddress("localhost",7233)
                        .usePlaintext()
                        .build();
        // Start a worker that hosts the workflow implementation.
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance(WorkflowServiceStubsOptions.newBuilder()
                .setChannel(managedChannel)
                .build());
        WorkflowClient client = WorkflowClient.newInstance(service);

        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker(TASK_QUEUE);


        worker.registerWorkflowImplementationTypes(LoadTestWorkFlowImpl.class);
        worker.registerActivitiesImplementations(new LoadTestActivityImpl());
        factory.start();
        System.out.println("Started Worker");




    }


}
