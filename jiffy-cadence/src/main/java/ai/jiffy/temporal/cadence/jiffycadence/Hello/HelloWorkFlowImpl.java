package ai.jiffy.temporal.cadence.jiffycadence.Hello;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

public class HelloWorkFlowImpl implements HelloWorkFlow {

    private static Logger logger = Workflow.getLogger(HelloWorkFlowImpl.class);

    private final HelloActivity activity =
            Workflow.newActivityStub(
                    HelloActivity.class,
                    ActivityOptions.newBuilder()
                            .setScheduleToCloseTimeout(Duration.ofHours(10)).build()
            );

    @Override
    public CompletableFuture<String> execute(String input) {
        logger.info("Welcome to workflow");
        CompletableFuture<String> completableFuture;
        logger.info("going to call activity");
        completableFuture = activity.apiCallTwo("ome");
        completableFuture.whenCompleteAsync((resp, err)->{
            logger.info("Workflow completed ...{}", resp);
        });
        return completableFuture;
        //CompletableFuture<String> output1 = activity.apiCallOne("one");
        //output1.thenAcceptAsync(result->activity::apiCallTwo,)
        //CompletableFuture<String> output2 = activity.apiCallTwo("two");
        //CompletableFuture<String> output3 = activity.apiCallThree("three");
        
        /*String output4 = activity.apiCallFour("four");
        String output5 = activity.apiCallFive("five");
        String output6 = activity.apiCallSix("six");
        String output7 = activity.apiCallSeven("seven");
        String output8 = activity.apiCallEight("eight");
        String output9 = activity.apiCallNine("nine");
        String output10 = activity.apiCallTen("ten");*/
        //return null;

    }
}
