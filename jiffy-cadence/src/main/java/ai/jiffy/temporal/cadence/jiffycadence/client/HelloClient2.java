package ai.jiffy.temporal.cadence.jiffycadence.client;

import ai.jiffy.temporal.cadence.jiffycadence.Hello.HelloWorkFlow;
import ai.jiffy.temporal.cadence.jiffycadence.config.CadenceWorker;
import io.temporal.client.WorkflowOptions;
import io.temporal.workflow.Workflow;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static ai.jiffy.temporal.cadence.jiffycadence.commons.StringConstant.TASK_QUEUE;

public class HelloClient2 {

    private static Logger logger = Workflow.getLogger(HelloClient.class);

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        String workflowId = RandomStringUtils.randomAlphabetic(10);

        HelloWorkFlow workFlow =
                CadenceWorker.client.newWorkflowStub(
                        HelloWorkFlow.class,
                        WorkflowOptions.newBuilder().setWorkflowId(workflowId).setTaskQueue(TASK_QUEUE).build()
                );
        logger.info("Starting client workflowId...{}",workflowId);
        //String outputs = workFlow.execute("Aswath");
        //logger.info(outputs);
        System.exit(0);
    }
}
