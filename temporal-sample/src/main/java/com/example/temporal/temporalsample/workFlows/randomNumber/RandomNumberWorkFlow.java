package com.example.temporal.temporalsample.workFlows.randomNumber;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface RandomNumberWorkFlow {
    @WorkflowMethod
    String runRandomNumber(int minValue, int maxValue);
}
