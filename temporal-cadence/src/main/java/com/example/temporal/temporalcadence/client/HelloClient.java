package com.example.temporal.temporalcadence.client;

import com.example.temporal.temporalcadence.config.CadenceWorker;
import com.example.temporal.temporalcadence.hello.HelloWorld;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.workflow.Workflow;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;

import java.util.concurrent.ExecutionException;

import static com.example.temporal.temporalcadence.commons.StringConstant.TASK_QUEUE;

public class HelloClient {

    private static Logger logger = Workflow.getLogger(HelloClient.class);

    public static void main(String[] args){

        // Start a worker that hosts the workflow implementation.
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        WorkflowClient client = WorkflowClient.newInstance(service);

        String workflowId = RandomStringUtils.randomAlphabetic(10);

        HelloWorld.HelloWorldWorkFlow workFlow =
                client.newWorkflowStub(
                        HelloWorld.HelloWorldWorkFlow.class,
                        WorkflowOptions.newBuilder().setWorkflowId(workflowId).setTaskQueue(TASK_QUEUE).build()
                );
        logger.info("Starting client workflowId...{}",workflowId);
        String outputs = workFlow.execute("Aswath");
        logger.info(outputs);
        System.exit(0);
    }
}
