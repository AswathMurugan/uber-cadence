package ai.jiffy.temporal.cadence.jiffycadence.botProcessor;

import ai.jiffy.temporal.cadence.jiffycadence.config.CadenceWorker;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

public class BotProcessorClient {

    private static Logger logger = Workflow.getLogger(BotProcessorClient.class);

    public static void start(String workFlowId, String taskQueue){
        WorkflowOptions workflowOptions =
                WorkflowOptions.newBuilder().setTaskQueue(taskQueue).setWorkflowId(workFlowId).build();
        ProcessorWorkFlow workflow = CadenceWorker.client.newWorkflowStub(ProcessorWorkFlow.class, workflowOptions);
        // Start workflow asynchronously to not use another thread to signal.
        WorkflowClient.start(workflow::execute);
        logger.info("Started Bot message processor workflowID: {} and Task queue: {}",workFlowId,taskQueue);
    }

}
