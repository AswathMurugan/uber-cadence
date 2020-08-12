package com.example.temporal.temporalsample.config;

import com.example.temporal.temporalsample.testsample.Async.AsyncActivityImp;
import com.example.temporal.temporalsample.workFlows.randomNumber.RandomNumberActivityImpl;
import com.example.temporal.temporalsample.workFlows.randomNumber.RandomNumberWorkFlow;
import com.example.temporal.temporalsample.workFlows.randomNumber.RandomNumberWorkFlowImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.temporal.temporalsample.commons.StringConstant.TASK_QUEUE_RANDOM;

@Configuration
public class MainWorker {

    private static Logger logger = Workflow.getLogger(MainWorker.class);

    @Bean
    public void workFlowWorker(){
        // gRPC stubs wrapper that talks to the local docker instance of temporal service.
        WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
        // client that can be used to start and signal workflows
        WorkflowClient client = WorkflowClient.newInstance(service);

        // worker factory that can be used to create workers for specific task queues
        WorkerFactory factory = WorkerFactory.newInstance(client);
        // Worker that listens on a task queue and hosts both workflow and activity implementations.
        Worker worker = factory.newWorker(TASK_QUEUE_RANDOM);
        worker.registerWorkflowImplementationTypes(RandomNumberWorkFlowImpl.class);
        worker.registerActivitiesImplementations(new RandomNumberActivityImpl());
        // Start listening to the workflow task queue.
        factory.start();
        logger.info("Started worker Task queue {}", worker.getTaskQueue());

    }
}
