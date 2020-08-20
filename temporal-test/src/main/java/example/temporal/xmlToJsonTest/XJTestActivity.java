package example.temporal.xmlToJsonTest;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface XJTestActivity {

    @ActivityMethod
    String xmlToJsonOne(String data);
}
