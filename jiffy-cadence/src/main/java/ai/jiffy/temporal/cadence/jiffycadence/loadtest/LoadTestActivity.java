package ai.jiffy.temporal.cadence.jiffycadence.loadtest;

import io.temporal.activity.ActivityInterface;

@ActivityInterface
public interface LoadTestActivity {

    String apiCallOne(String input);

    String apiCallTwo(String input);

    String apiCallThree(String input);

    String apiCallFour(String input);

    String apiCallFive(String input);

    String apiCallSix(String input);

    String apiCallSeven(String input);

    String apiCallEight(String input);

    String apiCallNine(String input);

    String apiCallTen(String input);
}
