package example.temporal.Async;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface AsyncActivity {

    @ActivityMethod
    String asyncApiCallOne();
}
