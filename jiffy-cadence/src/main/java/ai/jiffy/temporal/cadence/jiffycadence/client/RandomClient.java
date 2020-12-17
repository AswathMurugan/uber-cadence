package ai.jiffy.temporal.cadence.jiffycadence.client;

import ai.jiffy.temporal.cadence.jiffycadence.config.CadenceWorker;
import ai.jiffy.temporal.cadence.jiffycadence.customDSL.DSLWorkFlow;
import ai.jiffy.temporal.cadence.jiffycadence.sampleFlows.randomNumber.RandomNumberActivityWorkFlow;
import io.temporal.client.WorkflowOptions;
import io.temporal.workflow.Workflow;
import org.apache.commons.lang.RandomStringUtils;
import org.json.JSONException;
import org.slf4j.Logger;

import static ai.jiffy.temporal.cadence.jiffycadence.commons.StringConstant.RANDOM_JSON_INPUT;
import static ai.jiffy.temporal.cadence.jiffycadence.commons.StringConstant.TASK_QUEUE;

public class RandomClient {

    private static Logger logger = Workflow.getLogger(RandomClient.class);

    public static void main(String[] args) throws InterruptedException, JSONException {

        String workflowId = RandomStringUtils.randomAlphabetic(10);
        RandomNumberActivityWorkFlow workFlow =
                CadenceWorker.client.newWorkflowStub(
                        RandomNumberActivityWorkFlow.class,
                        WorkflowOptions.newBuilder()
                                .setWorkflowId(workflowId)
                                .setTaskQueue(TASK_QUEUE).build()
                );



        String outputs = workFlow.execute(RANDOM_JSON_INPUT,"BeginState");
        logger.info(outputs);
        System.exit(0);
    }
}
