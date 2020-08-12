package com.example.temporal.temporalsample.workFlows.randomNumber;

import java.util.Random;

public class RandomNumberActivityImpl implements RandomNumberActivity {
    @Override
    public int getRandomNumber(int minValue, int maxValue) {
        Random r = new Random();
        int result = r.nextInt(maxValue-minValue) + minValue;
        return result;
    }
}
