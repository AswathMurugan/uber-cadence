package ai.jiffy.temporal.cadence.jiffycadence.sampleFlows.randomNumber;

import ai.jiffy.temporal.cadence.jiffycadence.customDSL.DSLActivity;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.ActivityStub;
import io.temporal.workflow.Workflow;
import org.json.JSONException;
import org.slf4j.Logger;

import java.time.Duration;
import java.util.HashMap;

public class RandomNumberActivityWorkFlowImpl implements RandomNumberActivityWorkFlow {

    private static Logger logger = Workflow.getLogger(RandomNumberActivityWorkFlowImpl.class);

    private final ActivityOptions options =
            ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofMinutes(2))
                   .build();

    private final RandomNumberActivity randomNumberActivityActivity = Workflow.newActivityStub(
            RandomNumberActivity.class,options);

    private final ActivityStub activities =
            Workflow.newUntypedActivityStub(options);

    private HashMap<String,String> currentActivityMap;
    private String lastActivityResult;

    @Override
    public String execute(String data, String state) throws JSONException {
        randomNumberActivityActivity.loadDataRandomNumber(data);
        while (true){
            currentActivityMap = randomNumberActivityActivity
                    .getNextStepRandomNumber(state);
            if(currentActivityMap.isEmpty()){
                HashMap<String,Object> resp = randomNumberActivityActivity
                        .responseRandomNumber();
                return resp.toString();
            }
            logger.info("Current Activity data ....{}", currentActivityMap.toString());
            state = currentActivityMap.get("nextState");
            //execute
            lastActivityResult = activities.execute(currentActivityMap.get("activity"),
                    String.class, lastActivityResult);
        }
    }
}
