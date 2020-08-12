package com.example.temporal.temporalsample.workFlows.randomNumber;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

import java.time.Duration;

public class RandomNumberWorkFlowImpl implements RandomNumberWorkFlow{

    private static Logger logger = Workflow.getLogger(RandomNumberWorkFlowImpl.class);

    private final ActivityOptions options =
            ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofMinutes(2))
                    .build();

    private final RandomNumberActivity activity =
            Workflow.newActivityStub(
                    RandomNumberActivity.class,
                    options
            );

    @Override
    public String runRandomNumber(int minValue, int maxValue) {
        int result = activity.getRandomNumber(minValue,maxValue);
        return "";
    }
}
