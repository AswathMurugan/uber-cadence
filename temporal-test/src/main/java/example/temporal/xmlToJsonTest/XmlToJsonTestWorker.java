package example.temporal.xmlToJsonTest;

import example.temporal.loadtest.LoadTestActivityImpl;
import example.temporal.loadtest.LoadTestWorkFlowImpl;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

import java.util.concurrent.ExecutionException;

public class XmlToJsonTestWorker {

    public static String TASK_QUEUE = "xmlTOJson";



    public static void main(String[] args) throws ExecutionException, InterruptedException {

         ManagedChannel managedChannel =
                 ManagedChannelBuilder.forAddress("localhost",7233)
                         .maxInboundMessageSize(10716573)
                         .usePlaintext()
                         .build();
        // Start a worker that hosts the workflow implementation.
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance(WorkflowServiceStubsOptions.newBuilder()
                .setChannel(managedChannel)
                .build());
        WorkflowClient client = WorkflowClient.newInstance(service);



        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker(TASK_QUEUE);


        worker.registerWorkflowImplementationTypes(XJTestWorkFlowImpl.class);
        worker.registerActivitiesImplementations(new XJTestActivityImpl());
        factory.start();
        System.out.println("Started Worker XML to Json Workflow");




    }

}
