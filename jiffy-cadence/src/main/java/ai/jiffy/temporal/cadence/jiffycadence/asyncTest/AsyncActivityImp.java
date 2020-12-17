package ai.jiffy.temporal.cadence.jiffycadence.asyncTest;

import ai.jiffy.temporal.cadence.jiffycadence.commons.Utils;
import io.temporal.activity.Activity;
import io.temporal.activity.ActivityExecutionContext;
import io.temporal.client.ActivityCompletionClient;
import io.temporal.workflow.Promise;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

import static ai.jiffy.temporal.cadence.jiffycadence.commons.StringConstant.GET_URL;
import static ai.jiffy.temporal.cadence.jiffycadence.commons.StringConstant.GET_URL_SLEEEP;

public class AsyncActivityImp implements AsyncActivity {

    private static Logger logger = Workflow.getLogger(AsyncActivityImp.class);

    private final ActivityCompletionClient completionClient;

    public AsyncActivityImp(ActivityCompletionClient completionClient) {
        this.completionClient = completionClient;
    }


    @Override
    public String asyncApiCallOne() {

        ActivityExecutionContext context = Activity.getExecutionContext();
        byte[] taskToken = context.getTaskToken();

        try {
            Utils.asynTemporalGetStringHttpResponse(GET_URL,"one",taskToken,completionClient);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        context.doNotCompleteOnReturn();
        return "igoner";
    }

    /*@Override
    public String asyncApiCallTwo(String input) {
        // TaskToken is a correlation token used to match an activity task with its completion
        ActivityExecutionContext context = Activity.getExecutionContext();
        byte[] taskToken = context.getTaskToken();
        // In real life this request can be executed anywhere. By a separate service for
        // example.
        ForkJoinPool.commonPool().execute(() -> {
            try {
                Utils.asynTemporalGetStringHttpResponse(GET_URL_SLEEEP,input,taskToken,completionClient);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        context.doNotCompleteOnReturn();
        return "igoner";
    }*/
}
