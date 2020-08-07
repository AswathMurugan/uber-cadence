package com.example.temporal.temporalcadence.dsl.randomNumberGenter;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import org.json.JSONException;

import java.util.HashMap;

@ActivityInterface
public interface RNGActivity {

    @ActivityMethod
    HashMap<String,String> getNextStepRandomNumber(String currentState);

    @ActivityMethod
    void loadDataRandomNumber(String definitions) throws JSONException;

    @ActivityMethod
    HashMap<String, Object> responseRandomNumber();

    @ActivityMethod(name = "Activity::RandomNumber")
    Integer getRandomNumber() throws JSONException;
}
