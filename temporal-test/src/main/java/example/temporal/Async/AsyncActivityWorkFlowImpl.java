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
                            .setStartToCloseTimeout(Duration.ofMinutes(1))
                            .setScheduleToCloseTimeout(Duration.ofHours(6)).build()
            );

    @Override
    public String execute() {
        long time = Workflow.currentTimeMillis();
        //Promise<String> asyncCall = Async.function(()->activity.asyncApiCallOne());
        //String result = asyncCall.get();
        Workflow.sleep(Duration.ofSeconds(25));
        logger.info("WorkFlow id {} ...Execution time {}",Workflow.getInfo().getWorkflowId(), (Workflow.currentTimeMillis() - time)/1000);
        return "result";
    }
}
