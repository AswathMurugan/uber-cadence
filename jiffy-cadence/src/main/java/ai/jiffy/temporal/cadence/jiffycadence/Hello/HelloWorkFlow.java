package ai.jiffy.temporal.cadence.jiffycadence.Hello;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

import java.util.concurrent.CompletableFuture;


@WorkflowInterface
public interface HelloWorkFlow {

    @WorkflowMethod
    CompletableFuture<String> execute(String input);
}
