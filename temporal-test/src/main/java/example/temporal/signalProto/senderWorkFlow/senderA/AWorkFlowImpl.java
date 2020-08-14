package example.temporal.signalProto.senderWorkFlow.senderA;

import example.temporal.signalProto.processor.ProcessorWorkFlow;
import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

import java.util.HashMap;

public class AWorkFlowImpl implements AWorkFlow{

    private static Logger logger = Workflow.getLogger(AWorkFlowImpl.class);


    String response = "";

    @Override
    public String aExecute(String id,String sec) {

        //calling signal
        ProcessorWorkFlow processorWorkFlow =  Workflow.newExternalWorkflowStub
                (ProcessorWorkFlow.class, "WORKFLOW_SIGNAL_PROTO_ONE");
        HashMap<String ,String> tmpData = new HashMap<>();
        tmpData.put("id",Workflow.getInfo().getWorkflowId());
        tmpData.put("input",sec);
        tmpData.put("runId", Workflow.getInfo().getRunId());
        tmpData.put("signal","senderAResult");
        processorWorkFlow.waitForMessage(tmpData); //
        Workflow.await(()-> !response.isEmpty());
        if(response != null){
            logger.info(" Sender A Got the response from the signal processor");
            String result =  response;
            response = "";
            return result;
        }
        logger.info("Received nothing.");
        return "nothing";
    }

    @Override
    public void senderAResult(String result) {
        logger.info("Sender A signal got resp back from processer {}", result);
        response = result;
    }

}
