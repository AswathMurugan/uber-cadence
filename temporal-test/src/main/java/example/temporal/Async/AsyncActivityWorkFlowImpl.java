package example.temporal.Async;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Async;
import io.temporal.workflow.Promise;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

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
        Promise<String> asyncCall = Async.function(activity::asyncApiCallOne);
        long time = System.currentTimeMillis();
        String result = asyncCall.get();
        logger.info("Execution time {}", System.currentTimeMillis() - time);
        return result;
    }
}
