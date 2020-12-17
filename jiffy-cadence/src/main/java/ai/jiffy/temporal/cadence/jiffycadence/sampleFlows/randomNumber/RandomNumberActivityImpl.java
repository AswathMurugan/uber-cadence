package ai.jiffy.temporal.cadence.jiffycadence.sampleFlows.randomNumber;

import ai.jiffy.temporal.cadence.jiffycadence.commons.Utils;
import io.temporal.workflow.Workflow;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.HashMap;

import static ai.jiffy.temporal.cadence.jiffycadence.commons.StringConstant.*;

public class RandomNumberActivityImpl implements RandomNumberActivity {

    private static Logger logger = Workflow.getLogger(RandomNumberActivityImpl.class);
    private static JSONObject definitionsObj;
    private static String currentState;
    private static boolean endTask = false;

    private static String result;
    private static boolean verfiy;

    @Override
    public void loadDataRandomNumber(String definitions) throws JSONException {
        definitionsObj = new JSONObject(definitions);
    }


    @Override
    public HashMap<String, String> getNextStepRandomNumber(String state) {
        HashMap<String ,String> output = new HashMap<>();
        try{
            currentState = state;
            if(!endTask){
                String activity = definitionsObj.getJSONObject("States")
                        .getJSONObject(state).getString("Activity");
                String nextState = definitionsObj.getJSONObject("States")
                        .getJSONObject(state).getString("Next");
                endTask = definitionsObj.getJSONObject("States")
                        .getJSONObject(state).getBoolean("End");
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
    public String getRandomNumber() {
        try{
            JSONObject payload = definitionsObj.getJSONObject("States")
                    .getJSONObject("BeginState")
                    .getJSONObject("Payload");
            String input = payload.getString("Range");
            logger.info("Payload.... {}",payload);
            result = Utils.getStringHttpResponse(GET_URL_RANDOM,input).body();
            return result;
        }catch (Exception ex){
            logger.error("Exception while making external call..{}", ex);
        }

        return null;
    }

    @Override
    public HashMap<String, Object> responseRandomNumber() {
        endTask=false;
        HashMap<String,Object> response = new HashMap<>();
        if(verfiy){
            response.put("result",result);
        }
        return response;
    }

    @Override
    public Boolean verifyNumber() throws IOException, InterruptedException {
        logger.info("Stop worker here");
        String getSame = Utils.getStringHttpResponse(GET_URL_SLEEEP,result).body();
        if(Integer.parseInt(getSame) == Integer.parseInt(result)){
            verfiy = true;
            return true;
        }
        return false;
    }
}
