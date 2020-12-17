package ai.jiffy.temporal.cadence.jiffycadence.asyncTest;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

import java.io.IOException;

@ActivityInterface
public interface AsyncActivity {

    @ActivityMethod
    String asyncApiCallOne();

    //@ActivityMethod
    //String asyncApiCallTwo(String input) throws IOException, InterruptedException;
}
