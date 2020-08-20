package example.temporal.signalProto.senderWorkFlow.senderD;

import example.temporal.signalProto.senderWorkFlow.senderA.Aactivity;
import example.temporal.signalProto.senderWorkFlow.senderB.BWorkFlowImpl;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.ExternalWorkflowStub;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

import java.time.Duration;
import java.util.HashMap;

public class DWorkflowImpl implements DWorkflow {

    private static Logger logger = Workflow.getLogger(DWorkflowImpl.class);

    private final Dactivity activity =
            Workflow.newActivityStub(
                    Dactivity.class,
                    ActivityOptions.newBuilder()
                            .setStartToCloseTimeout(Duration.ofMinutes(5))
                            .setScheduleToCloseTimeout(Duration.ofHours(1)).build()
            );

    String response = "";
    @Override
    public void dExecute(String signalWorkFlowId, int id) {

        for(int i = 0; i<5; i++){
            //calling signal
            String  signal = callingActivity(signalWorkFlowId,"Signal_"+id+"."+i);
            logger.info("Response from signal processor {}", signal);
        }
    }

    private String callingActivity(String signalWorkFlowId,String input) {
        String workFlowId = Workflow.getInfo().getWorkflowId();
        String runId = Workflow.getInfo().getRunId();
        activity.signalActivity(signalWorkFlowId,workFlowId,runId,input);
        Workflow.await(() -> !response.isEmpty());
        if (response != null) {
            String result = response;
            response = "";
            return result;
        }
        return "";
    }

    @Override
    public void senderDResult(String result) {
        response = result;
    }
}
