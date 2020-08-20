package example.temporal.signalProto.senderWorkFlow.senderC;

import example.temporal.signalProto.SignalProtoWorker;
import example.temporal.signalProto.processor.ProcessorWorkFlow;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

import java.util.HashMap;

public class CWorkFlowImpl implements CWorkFlow {

    private static Logger logger = Workflow.getLogger(CWorkFlowImpl.class);

    String response = "";

    @Override
    public String cExecute(String id,String sec, String workflowName) {
        //calling signal
        ProcessorWorkFlow processorWorkFlow = Workflow.newExternalWorkflowStub
                (ProcessorWorkFlow.class, workflowName);
        HashMap<String, String> tmpData = new HashMap<>();
        tmpData.put("id", Workflow.getInfo().getWorkflowId());
        tmpData.put("input", sec);
        tmpData.put("runId", Workflow.getInfo().getRunId());
        tmpData.put("signal","senderCResult");
        processorWorkFlow.waitForMessage(tmpData); //
        Workflow.await(() -> !response.isEmpty());
        if (response != null) {
            logger.info(" Sender C Got the response from the signal processor");
            String result = response;
            response = "";
            return result;
        }
        logger.info("Received nothing.");
        return "nothing";
    }

    @Override
    public void senderCResult(String result) {
        response = result;
    }
}
