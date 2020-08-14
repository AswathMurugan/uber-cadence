package example.temporal.signalProto.processor;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface ProcessorActivity {

    @ActivityMethod
    String externalApiCall(String input);
}
