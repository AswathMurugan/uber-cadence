package com.example.temporal.temporalsample.testsample.Async;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface AsyncActivityWorkFlow {

    @WorkflowMethod
    String execute();
}
