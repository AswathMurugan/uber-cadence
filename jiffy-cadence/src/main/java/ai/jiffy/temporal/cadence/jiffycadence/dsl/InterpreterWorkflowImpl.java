package ai.jiffy.temporal.cadence.jiffycadence.dsl;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.ActivityStub;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

import java.time.Duration;

public class InterpreterWorkflowImpl implements InterpreterWorkflow {

    private static Logger logger = Workflow.getLogger(InterpreterWorkflowImpl.class);

    private final ActivityOptions options =
            ActivityOptions.newBuilder().setScheduleToCloseTimeout(Duration.ofHours(1)).build();

    private final Interpreter interpreter = Workflow.newActivityStub(Interpreter.class,options);

    private final ActivityStub activities =
            Workflow.newUntypedActivityStub(options);

    private String currentActivity = "init";
    private String lastActivityResult;

    @Override
    public String execute(String type, String input) {
        do {
            currentActivity = interpreter.getNextStep(type, currentActivity);
            if(currentActivity == null){
                return null;
            }
            lastActivityResult = activities.execute(currentActivity, String.class, lastActivityResult);
            logger.info("Thread ...{}..Current Activity ...{} and last Activity ...{}", Thread.currentThread().getName(),
                    currentActivity, lastActivityResult);

        }while (currentActivity != null);
        return null;
    }
}
