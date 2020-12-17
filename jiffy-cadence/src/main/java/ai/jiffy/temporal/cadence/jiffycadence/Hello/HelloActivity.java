package ai.jiffy.temporal.cadence.jiffycadence.Hello;

import io.temporal.activity.ActivityInterface;

import java.util.concurrent.CompletableFuture;

@ActivityInterface
public interface HelloActivity {


    CompletableFuture<String> apiCallOne(String input);


    CompletableFuture<String> apiCallTwo(String input);


    CompletableFuture<String> apiCallThree(String input);


   /* String apiCallFour(String input);

    String apiCallFive(String input);

    String apiCallSix(String input);

    String apiCallSeven(String input);

    String apiCallEight(String input);

    String apiCallNine(String input);

    String apiCallTen(String input);*/
}
