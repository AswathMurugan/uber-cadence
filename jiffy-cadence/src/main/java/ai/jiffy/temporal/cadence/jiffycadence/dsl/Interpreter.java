package ai.jiffy.temporal.cadence.jiffycadence.dsl;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;


@ActivityInterface
public interface Interpreter {

    String getNextStep(String workflowType, String lastActivity);

    @ActivityMethod(name = "Activity::ApiCallOneI")
    String apiCallOneI();

    String apiCallTwoI();


    String apiCallThreeI();


    String apiCallFourI();

    String apiCallFiveI();

    String apiCallSixI();

    String apiCallSevenI();

    String apiCallEightI();

    String apiCallNineI();

    String apiCallTenI();
}
