package ai.jiffy.temporal.cadence.jiffycadence.sampleFlows.randomNumber;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import org.json.JSONException;

@WorkflowInterface
public interface RandomNumberActivityWorkFlow {

    @WorkflowMethod
    String execute(String data, String state) throws JSONException;
}
