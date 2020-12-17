package ai.jiffy.temporal.cadence.jiffycadence.client;

import ai.jiffy.temporal.cadence.jiffycadence.config.CadenceWorker;
import ai.jiffy.temporal.cadence.jiffycadence.customDSL.DSLWorkFlow;
import ai.jiffy.temporal.cadence.jiffycadence.customDSL.DSLWorkFlowImpl;
import ai.jiffy.temporal.cadence.jiffycadence.dsl.InterpreterWorkflow;
import io.temporal.client.WorkflowOptions;
import io.temporal.workflow.Workflow;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;

import static ai.jiffy.temporal.cadence.jiffycadence.commons.StringConstant.TASK_QUEUE;

public class CustomDSLClient {

    private static Logger logger = Workflow.getLogger(CustomDSLClient.class);

    public static void main(String[] args) throws InterruptedException {

        String workflowId = RandomStringUtils.randomAlphabetic(10);
        DSLWorkFlow workFlow =
                CadenceWorker.client.newWorkflowStub(
                        DSLWorkFlow.class,
                        WorkflowOptions.newBuilder()
                                .setWorkflowId(workflowId)
                                .setTaskQueue(TASK_QUEUE).build()
                );



        String outputs = workFlow.execute("FirstState");
        logger.info(outputs);
        System.exit(0);
    }
}
