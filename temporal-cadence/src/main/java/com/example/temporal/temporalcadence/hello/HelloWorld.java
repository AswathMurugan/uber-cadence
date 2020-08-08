package com.example.temporal.temporalcadence.hello;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import org.slf4j.Logger;

import java.time.Duration;

public class HelloWorld {

    private static Logger logger = Workflow.getLogger(HelloWorld.class);

    @ActivityInterface
    public interface HelloWorldActivity{

        @ActivityMethod
        String getString(String name);
    }

    @WorkflowInterface
    public interface HelloWorldWorkFlow{
        @WorkflowMethod
        String execute(String name);
    }

    public static class HelloWorldWorkFlowImpl implements HelloWorldWorkFlow{

        private final HelloWorldActivity activity =
                Workflow.newActivityStub(
                        HelloWorldActivity.class,
                        ActivityOptions.newBuilder()
                                .setStartToCloseTimeout(Duration.ofSeconds(10))
                                .setScheduleToCloseTimeout(Duration.ofMinutes(1)).build()
                );

        @Override
        public String execute(String name) {
            return activity.getString(name);
        }
    }

    public static class HelloWorldActivityImpl implements HelloWorldActivity{

        @Override
        public String getString(String name) {
            return "Welcome " + name;
        }
    }
}
