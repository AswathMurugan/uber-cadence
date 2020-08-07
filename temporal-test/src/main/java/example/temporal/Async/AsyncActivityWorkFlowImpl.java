package example.temporal.Async;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Async;
import io.temporal.workflow.Promise;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

import java.time.Duration;

public class AsyncActivityWorkFlowImpl implements AsyncActivityWorkFlow {

    private static Logger logger = Workflow.getLogger(AsyncActivityWorkFlowImpl.class);

    private final AsyncActivity activity =
            Workflow.newActivityStub(
                    AsyncActivity.class,
                    ActivityOptions.newBuilder()
                            .setScheduleToCloseTimeout(Duration.ofMinutes(15)).build()
            );

    @Override
    public String execute() {

        Promise<String> asyncApiCallOne = Async.function(activity::asyncApiCallOne);
        asyncApiCallOne.thenApply(resp ->{
                    logger.info("Commplete workflow {}", resp);
                    return resp;
                });


        return asyncApiCallOne.get();
    }
}
