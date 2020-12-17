package ai.jiffy.temporal.cadence.jiffycadence.client;

import ai.jiffy.temporal.cadence.jiffycadence.Hello.HelloWorkFlow;
import ai.jiffy.temporal.cadence.jiffycadence.config.CadenceWorker;
import ai.jiffy.temporal.cadence.jiffycadence.dsl.InterpreterWorkflow;
import io.temporal.client.WorkflowOptions;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

import java.util.stream.IntStream;

import static ai.jiffy.temporal.cadence.jiffycadence.commons.StringConstant.TASK_QUEUE;

public class InterpreterClient {

    private static Logger logger = Workflow.getLogger(InterpreterClient.class);

    public static void main(String[] args) throws InterruptedException {
        InterpreterWorkflow workFlow =
                CadenceWorker.client.newWorkflowStub(
                        InterpreterWorkflow.class,
                        WorkflowOptions.newBuilder().setTaskQueue(TASK_QUEUE).build()
                );



        String outputs = workFlow.execute("type1", "input1");
        logger.info(outputs);
        System.exit(0);
    }
}
