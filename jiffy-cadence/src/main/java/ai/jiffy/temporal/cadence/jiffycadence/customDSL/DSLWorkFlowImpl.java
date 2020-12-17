package ai.jiffy.temporal.cadence.jiffycadence.customDSL;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.ActivityStub;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

import java.time.Duration;

public class DSLWorkFlowImpl implements DSLWorkFlow {
    private static Logger logger = Workflow.getLogger(DSLWorkFlowImpl.class);
    private final ActivityOptions options =
            ActivityOptions.newBuilder().setScheduleToCloseTimeout(Duration.ofHours(1)).build();

    private final DSLActivity dslActivity = Workflow.newActivityStub(DSLActivity.class,options);

    private final ActivityStub activities =
            Workflow.newUntypedActivityStub(options);

    private String currentActivity;
    private String lastActivityResult;

    @Override
    public String execute(String state) {
        do{

            currentActivity = dslActivity.getActivity(state);
            if(currentActivity == null){
                return null;
            }
            logger.info("Current state..{} and Activity ..{}", state,currentActivity);
            //execute
            lastActivityResult = activities.execute(currentActivity, String.class, lastActivityResult);
            state = dslActivity.getNextState(state);

        }while (currentActivity != null);
        return null;
    }
}
