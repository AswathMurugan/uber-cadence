package example.temporal.signalProto.senderWorkFlow.senderB;

import io.temporal.workflow.ExternalWorkflowStub;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BWorkFlowImpl implements BWorkFlow {

    private static Logger logger = Workflow.getLogger(BWorkFlowImpl.class);

    String response = "";

    @Override
    public void bExecute(String signalWorkFlowId,int id) {

        for(int i = 0; i<5; i++){
            //calling signal
            String  signal = callingSignal(signalWorkFlowId,"Signal_"+id+"."+i);
            logger.info("Response from signal processor {}", signal);
        }

    }

    private String callingSignal(String signalWorkFlowId,String input) {
        String workFlowId = Workflow.getInfo().getWorkflowId();
        String runId = Workflow.getInfo().getRunId();
        ExternalWorkflowStub processorSignal = Workflow.newUntypedExternalWorkflowStub(signalWorkFlowId);
        HashMap<String, String> data = messageBuilder(workFlowId,input,runId);
        processorSignal.signal("waitForMessage", data);
        Workflow.await(() -> !response.isEmpty());
        if (response != null) {
            String result = response;
            response = "";
            return result;
        }
        return "";
    }

    private HashMap<String, String> messageBuilder(String workflowId,String input,String runId) {
        HashMap<String ,String> data = new HashMap<>();
        data.put("id", workflowId);
        data.put("input",input);
        data.put("runId", runId);
        data.put("signal","senderBResult");
        return data;
    }

    @Override
    public void senderBResult(String result) {
        response = result;
    }
}
