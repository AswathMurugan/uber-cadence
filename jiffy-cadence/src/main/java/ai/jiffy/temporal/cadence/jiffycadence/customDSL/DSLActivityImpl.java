package ai.jiffy.temporal.cadence.jiffycadence.customDSL;

import ai.jiffy.temporal.cadence.jiffycadence.dsl.InterpreterImpl;
import io.temporal.workflow.Workflow;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static ai.jiffy.temporal.cadence.jiffycadence.commons.StringConstant.GET_URL;

public class DSLActivityImpl implements DSLActivity {

    private static Logger logger = Workflow.getLogger(DSLActivityImpl.class);

    private static JSONObject definitionsObj;

    private static String currentState;

    private static String result;

    public DSLActivityImpl(String definitions) throws JSONException {
        definitionsObj = new JSONObject(definitions);
    }

    @Override
    public String getActivity(String state) {
        try {
            currentState = state;
            if(state.equalsIgnoreCase("null")){
                return null;
            }
            String nextState = definitionsObj.getJSONObject("States").getJSONObject(state).getString("NextState");
            if(nextState != null){
                String stateTransit = definitionsObj.getJSONObject("States").getJSONObject(state).getString("Activity");
                return stateTransit;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public String getNextState(String state) {
        try {
            String nextState = definitionsObj.getJSONObject("States").getJSONObject(state).getString("NextState");
            return nextState;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String customApiCallOne() {
        try{
            String input = definitionsObj.getJSONObject("States").getJSONObject(currentState).getString("Input");
            result = getStringHttpResponse(GET_URL,input+",one").body();
            return result;
        }catch (Exception ex){

        }
        return null;
    }

    @Override
    public String customApiCallTwo() {
        try{
            String input = definitionsObj.getJSONObject("States").getJSONObject(currentState).getString("Input");
            if(input.equalsIgnoreCase("result")){
                result = getStringHttpResponse(GET_URL,result+",two").body();
                return result;
            }
        }catch (Exception ex){

        }
        return null;
    }

    @Override
    public String customApiCallThree() {
        try{
            String input = definitionsObj.getJSONObject("States").getJSONObject(currentState).getString("Input");
            if(input.equalsIgnoreCase("result")){
                result = getStringHttpResponse(GET_URL,result+",three").body();
                return result;
            }
        }catch (Exception ex){

        }
        return null;
    }

    static HttpResponse<String> getStringHttpResponse(String url, String val) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String formatURL = String.format(url, val);
        logger.info("Thread..{} Calling External API get request....{}",Thread.currentThread().getName(),
                formatURL);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(formatURL))
                .build();

        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
