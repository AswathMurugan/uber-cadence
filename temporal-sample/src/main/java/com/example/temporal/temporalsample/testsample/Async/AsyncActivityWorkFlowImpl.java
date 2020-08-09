package com.example.temporal.temporalsample.testsample.Async;

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
                            .setStartToCloseTimeout(Duration.ofMinutes(1))
                            .setScheduleToCloseTimeout(Duration.ofHours(6)).build()
            );

    @Override
    public String execute() {
        Promise<String> asyncCall = Async.function(()->activity.asyncApiCallOne());
        long time = System.currentTimeMillis();
        String result = asyncCall.get();
        logger.info("Execution time {}", (System.currentTimeMillis() - time)/1000);
        return result;
    }
}
