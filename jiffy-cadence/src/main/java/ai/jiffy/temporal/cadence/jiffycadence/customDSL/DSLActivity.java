package ai.jiffy.temporal.cadence.jiffycadence.customDSL;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface DSLActivity {

    String getActivity(String State);

    String getNextState(String state);

    @ActivityMethod(name = "Activity::apiOne")
    String customApiCallOne();

    @ActivityMethod(name = "Activity::apiTwo")
    String customApiCallTwo();

    @ActivityMethod(name = "Activity::apiThree")
    String customApiCallThree();
}
