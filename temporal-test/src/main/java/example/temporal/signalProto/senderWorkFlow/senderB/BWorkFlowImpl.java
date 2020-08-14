package example.temporal.signalProto.senderWorkFlow.senderB;

import example.temporal.signalProto.SignalProtoWorker;
import example.temporal.signalProto.processor.ProcessorWorkFlow;
import example.temporal.signalProto.senderWorkFlow.senderA.AWorkFlowImpl;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

import java.util.HashMap;

public class BWorkFlowImpl implements BWorkFlow {

    private static Logger logger = Workflow.getLogger(BWorkFlowImpl.class);

    String response = "";

    @Override
    public String bExecute(String id,String sec) {
        //calling signal
        ProcessorWorkFlow processorWorkFlow =  Workflow.newExternalWorkflowStub
                (ProcessorWorkFlow.class, "WORKFLOW_SIGNAL_PROTO_ONE");
        HashMap<String ,String> tmpData = new HashMap<>();
        tmpData.put("id",Workflow.getInfo().getWorkflowId());
        tmpData.put("input",sec);
        tmpData.put("runId", Workflow.getInfo().getRunId());
        tmpData.put("signal","senderBResult");
        processorWorkFlow.waitForMessage(tmpData); //
        Workflow.await(()-> !response.isEmpty());
        if(response != null){
            logger.info(" Sender B Got the response from the signal processor");
            String result =  response;
            response = "";
            return result;
        }
        logger.info("Received nothing.");
        return "nothing";

    }

    @Override
    public void senderBResult(String result) {
        response = result;
    }
}
