package com.example.temporal.temporalcadence.dsl.randomNumberGenter;

import io.temporal.client.ActivityCompletionClient;
import io.temporal.workflow.Workflow;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;

import java.util.HashMap;
import java.util.Random;

public class RNGActivityImpl implements  RNGActivity{

    private static Logger logger = Workflow.getLogger(RNGActivityImpl.class);

    private static JSONObject definitionsObj;
    private static boolean endTask = false;
    private static int result;

    @Override
    public HashMap<String, String> getNextStepRandomNumber(String currentState) {
        HashMap<String ,String> output = new HashMap<>();
        try{
            if(!endTask){
                String activity = definitionsObj.getJSONObject("States")
                        .getJSONObject(currentState).getString("Activity");
                String nextState = definitionsObj.getJSONObject("States")
                        .getJSONObject(currentState).getString("Next");
                endTask = definitionsObj.getJSONObject("States")
                        .getJSONObject(currentState).getBoolean("End");
                output.put("activity",activity);
                output.put("nextState",nextState);
                logger.info("Current activity ..{} and next state ..{} and end state..{}",
                        activity,nextState,endTask);
                return output;
            }

        }catch (Exception ex){
            logger.error("Exception while getting activity from input..{}", ex);
        }
        return output;
    }

    @Override
    public void loadDataRandomNumber(String definitions) throws JSONException {
        definitionsObj = new JSONObject(definitions);
    }

    @Override
    public HashMap<String, Object> responseRandomNumber() {
        endTask=false;
        HashMap<String,Object> response = new HashMap<>();
        response.put("result",result);
        return response;
    }

    @Override
    public Integer getRandomNumber() throws JSONException {

        JSONObject payload = definitionsObj.getJSONObject("States")
                .getJSONObject("BeginState")
                .getJSONObject("Payload");
        Integer low = payload.getInt("From");
        Integer high = payload.getInt("To");

        Random r = new Random();
        result = r.nextInt(high-low) + low;
        return result;
    }
}
