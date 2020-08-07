package com.example.temporal.temporalcadence.dsl.randomNumberGenter;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import org.json.JSONException;

@WorkflowInterface
public interface RNGActivityWorkFlow {

    @WorkflowMethod
    String execute(String data, String state) throws JSONException;
}
