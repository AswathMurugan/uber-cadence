package com.example.temporal.temporalcadence.client;

import com.example.temporal.temporalcadence.config.CadenceWorker;
import com.example.temporal.temporalcadence.dsl.randomNumberGenter.RNGActivityWorkFlow;
import io.temporal.client.WorkflowOptions;
import io.temporal.workflow.Workflow;
import org.apache.commons.lang.RandomStringUtils;
import org.json.JSONException;
import org.slf4j.Logger;

import static com.example.temporal.temporalcadence.commons.StringConstant.TASK_QUEUE;

public class RNGClient {
    private static Logger logger = Workflow.getLogger(RNGClient.class);

    public static void main(String[] args) throws JSONException {

        String workflowId = RandomStringUtils.randomAlphabetic(10);
        RNGActivityWorkFlow workFlow =
                CadenceWorker.client.newWorkflowStub(
                        RNGActivityWorkFlow.class,
                        WorkflowOptions.newBuilder()
                                .setWorkflowId(workflowId)
                                .setTaskQueue(TASK_QUEUE).build()
                );

        String RANDOM_JSON_INPUT = "{\"Id\":\"KYQRHskdWc\",\"Comment\":\"Simple workflow for generate random number\",\"StartAt\":\"BeginState\",\"States\":{\"BeginState\":{\"Type\":\"TASK\",\"TaskName\":\"\",\"Activity\":\"Activity::RandomNumber\",\"Payload\":{\"From\":10,\"To\":100},\"End\":true,\"Next\":\"\"}}}";

        String outputs = workFlow.execute(RANDOM_JSON_INPUT,"BeginState");
        logger.info(outputs);
        System.exit(0);

    }
}
