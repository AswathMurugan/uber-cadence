package com.example.temporal.temporalsample.workFlows.randomNumber;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface RandomNumberActivity {

    @ActivityMethod
    int getRandomNumber(int minValue, int maxValue);

}
