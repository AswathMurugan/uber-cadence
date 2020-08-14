package example.temporal.signalProto.processor;

import io.temporal.activity.ActivityOptions;
import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.workflow.ExternalWorkflowStub;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProcessorWorkFlowImpl implements ProcessorWorkFlow {

    private static Logger logger = Workflow.getLogger(ProcessorWorkFlowImpl.class);

    private final ProcessorActivity activity =
            Workflow.newActivityStub(
                    ProcessorActivity.class,
                    ActivityOptions.newBuilder()
                            .setStartToCloseTimeout(Duration.ofMinutes(25))
                            .setScheduleToCloseTimeout(Duration.ofHours(6)).build()
            );

    List<HashMap<String, String>> messageQueue = new ArrayList<>();
    HashMap<String, String> completedMessage = new HashMap<>();
    boolean exit = false;

    @Override
    public String execute() {
        List<HashMap<String, String>> receivedMessages = new ArrayList<>();
        while (true){
            Workflow.await(()-> !messageQueue.isEmpty() || exit);
            if (messageQueue.isEmpty() && exit) {
                return "Exit";
            }
            HashMap<String, String> message = messageQueue.remove(0);
            String response = activity.externalApiCall(message.get("input"));
            WorkflowExecution execution = WorkflowExecution.newBuilder().setWorkflowId(message.get("id"))
                    .setRunId(message.get("runId"))
                    .build();

            ExternalWorkflowStub externalWorkflowStub = Workflow.newUntypedExternalWorkflowStub(execution);
            logger.info("Sending signal to work flow using workflow id....{}", message.get("id"));
            externalWorkflowStub.signal(message.get("signal"),response);
        }
    }

    //Signal
    @Override
    public void waitForMessage(HashMap<String, String> inputMessage) {
        logger.info("@Signal data ..{}" ,inputMessage);
        messageQueue.add(inputMessage);
    }

    //Signal
    @Override
    public void exit() {
        exit = true;
    }
}
