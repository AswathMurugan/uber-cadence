package com.example.temporal.temporalcadence.config;

import com.example.temporal.temporalcadence.dsl.randomNumberGenter.RNGActivityImpl;
import com.example.temporal.temporalcadence.dsl.randomNumberGenter.RNGActivityWorkFlowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import io.temporal.workflow.Workflow;
import org.json.JSONException;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.temporal.temporalcadence.commons.StringConstant.TASK_QUEUE;

@Configuration
public class CadenceWorker {


    private static Logger logger = Workflow.getLogger(CadenceWorker.class);

    // Start a worker that hosts the workflow implementation.
    public static WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
    public static WorkflowClient client = WorkflowClient.newInstance(service);

    @Bean
    public void worker() throws JSONException {
        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker(TASK_QUEUE);

        worker.registerWorkflowImplementationTypes(RNGActivityWorkFlowImpl.class);
        worker.registerActivitiesImplementations(new RNGActivityImpl());
        factory.start();
        logger.info("Thread..{} Starting the workflow task name:..{}",Thread.currentThread().getName(), TASK_QUEUE);
    }


}
