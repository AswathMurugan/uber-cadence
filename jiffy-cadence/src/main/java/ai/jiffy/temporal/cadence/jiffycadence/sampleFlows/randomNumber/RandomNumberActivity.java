package ai.jiffy.temporal.cadence.jiffycadence.sampleFlows.randomNumber;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;

@ActivityInterface
public interface RandomNumberActivity {

    @ActivityMethod
    HashMap<String,String> getNextStepRandomNumber(String currentState);

    @ActivityMethod
    void loadDataRandomNumber(String state) throws JSONException;

    @ActivityMethod(name = "Activity::RandomNumber")
    String getRandomNumber();

    @ActivityMethod
    HashMap<String, Object> responseRandomNumber();

    @ActivityMethod(name = "Activity::RandomNumberVerify")
    Boolean verifyNumber() throws IOException, InterruptedException;

}
