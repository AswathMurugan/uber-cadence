package example.temporal.signalProto.senderWorkFlow.senderA;

import example.temporal.signalProto.SignalProtoWorker;
import example.temporal.signalProto.processor.ProcessorWorkFlow;
import io.temporal.client.WorkflowStub;
import io.temporal.workflow.*;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Optional;

import static io.temporal.workflow.Workflow.await;

public class AactivityImpl implements Aactivity {

    private static Logger logger = Workflow.getLogger(AactivityImpl.class);

    String response = "";
    boolean isComplete = false;

    @Override
    public void updateResponse(String response) {
        this.response = response;
        this.isComplete = true;
    }

    @Override
    public String activity_1(String signalWorkFlowId, String workflowId, String runId) {
        WorkflowStub processorWorkFlow = SignalProtoWorker.client
                .newUntypedWorkflowStub(signalWorkFlowId, Optional.empty(),Optional.empty());
        processorWorkFlow.signal("waitForMessage", buildRequestData(workflowId, runId, "Activity_1"));
        while (true){
            logger.info("Waiting for signal from Signal processor");
            if(isComplete){
                String result = getResult(response, "Activity_1");
                return result;
            }
        }
    }



    @Override
    public String activity_2(String signalWorkFlowId, String workflowId, String runId) {
        WorkflowStub processorWorkFlow = SignalProtoWorker.client
                .newUntypedWorkflowStub(signalWorkFlowId, Optional.empty(),Optional.empty());
        processorWorkFlow.signal("waitForMessage", buildRequestData(workflowId, runId, "Activity_2"));
        while (true) {
            logger.info("Waiting for signal from Signal processor");
            if (isComplete) {
                String result = getResult(response, "Activity_2");
                return result;
            }
        }
    }

    @Override
    public String activity_3(String signalWorkFlowId, String workflowId, String runId) {
        WorkflowStub processorWorkFlow = SignalProtoWorker.client
                .newUntypedWorkflowStub(signalWorkFlowId, Optional.empty(),Optional.empty());
        processorWorkFlow.signal("waitForMessage", buildRequestData(workflowId, runId, "Activity_3"));
        while (true){
            logger.info("Waiting for signal from Signal processor");
            if(isComplete){
                String result = getResult(response, "Activity_3");
                return result;
            }
        }
    }

    @Override
    public String activity_4(String signalWorkFlowId, String workflowId, String runId) {
        WorkflowStub processorWorkFlow = SignalProtoWorker.client
                .newUntypedWorkflowStub(signalWorkFlowId, Optional.empty(),Optional.empty());
        processorWorkFlow.signal("waitForMessage", buildRequestData(workflowId, runId, "Activity_4"));
        while (true){
            logger.info("Waiting for signal from Signal processor");
            if(isComplete){
                String result = getResult(response, "Activity_4");
                return result;
            }
        }
    }

    @Override
    public String activity_5(String signalWorkFlowId, String workflowId, String runId) {
        WorkflowStub processorWorkFlow = SignalProtoWorker.client
                .newUntypedWorkflowStub(signalWorkFlowId, Optional.empty(),Optional.empty());
        processorWorkFlow.signal("waitForMessage", buildRequestData(workflowId, runId, "Activity_5"));
        while (true){
            logger.info("Waiting for signal from Signal processor");
            if(isComplete){
                String result = getResult(response, "Activity_5");
                return result;
            }
        }
    }

    @Override
    public String activity_6(String signalWorkFlowId, String workflowId, String runId) {
        WorkflowStub processorWorkFlow = SignalProtoWorker.client
                .newUntypedWorkflowStub(signalWorkFlowId, Optional.empty(),Optional.empty());
        processorWorkFlow.signal("waitForMessage", buildRequestData(workflowId, runId, "Activity_6"));
        while (true){
            logger.info("Waiting for signal from Signal processor");
            if(isComplete){
                String result = getResult(response, "Activity_6");
                return result;
            }
        }
    }

    @Override
    public String activity_7(String signalWorkFlowId, String workflowId, String runId) {
        WorkflowStub processorWorkFlow = SignalProtoWorker.client
                .newUntypedWorkflowStub(signalWorkFlowId, Optional.empty(),Optional.empty());
        processorWorkFlow.signal("waitForMessage", buildRequestData(workflowId, runId, "Activity_7"));
        while (true){
            logger.info("Waiting for signal from Signal processor");
            if(isComplete){
                String result = getResult(response, "Activity_7");
                return result;
            }
        }
    }

    @Override
    public String activity_8(String signalWorkFlowId, String workflowId, String runId) {
        WorkflowStub processorWorkFlow = SignalProtoWorker.client
                .newUntypedWorkflowStub(signalWorkFlowId, Optional.empty(),Optional.empty());
        processorWorkFlow.signal("waitForMessage", buildRequestData(workflowId, runId, "Activity_8"));
        while (true){
            logger.info("Waiting for signal from Signal processor");
            if(isComplete){
                String result = getResult(response, "Activity_8");
                return result;
            }
        }
    }

    @Override
    public String activity_9(String signalWorkFlowId, String workflowId, String runId) {
        WorkflowStub processorWorkFlow = SignalProtoWorker.client
                .newUntypedWorkflowStub(signalWorkFlowId, Optional.empty(),Optional.empty());
        processorWorkFlow.signal("waitForMessage", buildRequestData(workflowId, runId, "Activity_9"));
        while (true){
            logger.info("Waiting for signal from Signal processor");
            if(isComplete){
                String result = getResult(response, "Activity_9");
                return result;
            }
        }
    }

    @Override
    public String activity_10(String signalWorkFlowId, String workflowId, String runId) {
        WorkflowStub processorWorkFlow = SignalProtoWorker.client
                .newUntypedWorkflowStub(signalWorkFlowId, Optional.empty(),Optional.empty());
        processorWorkFlow.signal("waitForMessage", buildRequestData(workflowId, runId, "Activity_10"));
        while (true){
            logger.info("Waiting for signal from Signal processor");
            if(isComplete){
                String result = getResult(response, "Activity_10");
                return result;
            }
        }
    }

    private String getResult(String result, String activityName){
        logger.info("Activity name {} Got response from signal processor {}", activityName, result);
        response = "";
        isComplete= false;
        return result;
    }

    private HashMap<String, String> buildRequestData(String workflowId, String runId, String activity_1) {
        HashMap<String, String> tmpData = new HashMap<>();
        tmpData.put("id", workflowId);
        tmpData.put("input", activity_1);
        tmpData.put("runId", runId);
        tmpData.put("signal", "senderAResult");
        return tmpData;
    }
}
