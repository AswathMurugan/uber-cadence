package com.example.temporal.temporalcadence.commons;

public class StringConstant {



    public static String TASK_QUEUE = "HelloActive";

    public static String GET_URL = "http://localhost:9012/ca/%s";
    public static String GET_URL_SLEEEP = "http://localhost:9012/ca/sleep/%s";

    public static String GET_URL_RANDOM = "http://localhost:9012/ca/random/%s";


    public static String DSL_JSON_INPUT = "{\"StartAt\":\"FirstState\",\"States\":{\"FirstState\":{\"Type\":\"Task\",\"Activity\":\"Activity::apiOne\",\"Input\":\"result\",\"Output\":\"result\",\"NextState\":\"SecondState\",\"Previous\":\"null\"},\"SecondState\":{\"Type\":\"Task\",\"Activity\":\"Activity::apiTwo\",\"Input\":\"result\",\"Output\":\"result\",\"NextState\":\"ThirdState\",\"Previous\":\"FirstState\"},\"ThirdState\":{\"Type\":\"Task\",\"Activity\":\"Activity::apiThree\",\"Input\":\"result\",\"Output\":\"result\",\"NextState\":\"null\",\"Previous\":\"ThirdState\"}},\"DefaultState\":{\"Type\":\"Fail\",\"Error\":\"DefaultStateError\",\"Cause\":\"No Matches!\"}}";

    //public static String RANDOM_JSON_INPUT ="{\"Id\":\"KYQRHskdWc\",\"Comment\":\"Simple workflow for generate random number\",\"StartAt\":\"BeginState\",\"States\":{\"BeginState\":{\"Type\":\"TASK\",\"TaskName\":\"\",\"Activity\":\"Activity::RandomNumber\",\"Payload\":{\"Range\":10},\"End\":true,\"Next\":\"\"}}}";

    public static String RANDOM_JSON_INPUT = "{\"Id\":\"KYQRHskdWc\",\"Comment\":\"Simple workflow for generate random number\",\"StartAt\":\"BeginState\",\"States\":{\"BeginState\":{\"Type\":\"TASK\",\"TaskName\":\"\",\"Activity\":\"Activity::RandomNumber\",\"Payload\":{\"Range\":10},\"End\":false,\"Next\":\"VerifyState\"},\"VerifyState\":{\"Type\":\"TASK\",\"TaskName\":\"\",\"Activity\":\"Activity::RandomNumberVerify\",\"End\":true,\"Next\":\"\"}}}";
}
