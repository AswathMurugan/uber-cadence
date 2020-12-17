package ai.jiffy.temporal.cadence.jiffycadence.loadtest;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

import java.time.Duration;
import java.util.Random;

public class LoadTestWorkFlowImpl implements LoadTestWorkFlow {
    private static Logger logger = Workflow.getLogger(LoadTestWorkFlowImpl.class);

    private final LoadTestActivity activity =
            Workflow.newActivityStub(
                    LoadTestActivity.class,
                    ActivityOptions.newBuilder()
                            .setStartToCloseTimeout(Duration.ofMinutes(5))
                            .setScheduleToCloseTimeout(Duration.ofHours(1)).build()
            );
    @Override
    public String runWorkFlow() {
        long time = Workflow.currentTimeMillis();
        activity.apiCallOne(getRandomNumber());
        activity.apiCallTwo(getRandomNumber());
        activity.apiCallThree(getRandomNumber());
        activity.apiCallFour(getRandomNumber());
        activity.apiCallFive(getRandomNumber());
        activity.apiCallSix(getRandomNumber());
        activity.apiCallSeven(getRandomNumber());
        activity.apiCallEight(getRandomNumber());
        activity.apiCallNine(getRandomNumber());
        activity.apiCallTen(getRandomNumber());
        //Workflow.sleep(Duration.ofSeconds(25));
        logger.info("WorkFlow id {} ...Execution time in Sec {}",Workflow.getInfo().getWorkflowId(), (Workflow.currentTimeMillis() - time)/1000);
        return "result";
    }

    private static String getRandomNumber(){
        // create instance of Random class
        Random rand = new Random();
        int rand_int1 = rand.nextInt(30);
        return String.valueOf(rand_int1);
    }
}
