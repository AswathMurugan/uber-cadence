package example.temporal.signalProto.senderWorkFlow.senderA;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AWorkFlowImpl implements AWorkFlow{

    private static Logger logger = Workflow.getLogger(AWorkFlowImpl.class);

    private final Aactivity activity =
            Workflow.newActivityStub(
                    Aactivity.class,
                    ActivityOptions.newBuilder()
                            .setStartToCloseTimeout(Duration.ofMinutes(5))
                            .setScheduleToCloseTimeout(Duration.ofHours(1)).build()
            );


    @Override
    public List<String> aExecute(String id, String sec, String workflowName) {
        long time = Workflow.currentTimeMillis();
        List<String > responseData = new ArrayList<>();
        String workFlowId = Workflow.getInfo().getWorkflowId();
        String runId = Workflow.getInfo().getRunId();
        String activity_1 = activity.activity_1(workflowName, workFlowId, runId);
        responseData.add(activity_1);
        String activity_2 = activity.activity_2(workflowName, workFlowId, runId);
        responseData.add(activity_2);
        String activity_3 = activity.activity_3(workflowName, workFlowId, runId);
        responseData.add(activity_3);
        String activity_4 = activity.activity_4(workflowName, workFlowId, runId);
        responseData.add(activity_4);
        String activity_5 = activity.activity_5(workflowName, workFlowId, runId);
        responseData.add(activity_5);
        String activity_6 = activity.activity_6(workflowName, workFlowId, runId);
        responseData.add(activity_6);
        String activity_7 = activity.activity_7(workflowName, workFlowId, runId);
        responseData.add(activity_7);
        String activity_8 = activity.activity_8(workflowName, workFlowId, runId);
        responseData.add(activity_8);
        String activity_9 = activity.activity_9(workflowName, workFlowId, runId);
        responseData.add(activity_9);
        String activity_10 = activity.activity_10(workflowName, workFlowId, runId);
        responseData.add(activity_10);
        logger.info("Sender A workflow completed ..{}", responseData.toString());
        logger.info("execution time : {}",((Workflow.currentTimeMillis() - time)/1000));
        return responseData;
    }

    @Override
    public void senderAResult(String result) {
        activity.updateResponse(result);
    }

}
