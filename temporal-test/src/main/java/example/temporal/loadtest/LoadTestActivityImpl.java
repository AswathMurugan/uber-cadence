package example.temporal.loadtest;

import io.temporal.workflow.Workflow;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class LoadTestActivityImpl implements LoadTestActivity {
    //private static String GET_URL = "http://localhost:9013/ca/%s";
    public static String GET_URL_SLEEP = "http://localhost:9012/ca/sleep/%s";
    public static String GET_URL_SLEEP_Next = "http://localhost:9013/ca/sleep/%s";
    @Override
    public String apiCallOne(String input) {
        return getStringHttpResponse(GET_URL_SLEEP,input);
    }

    @Override
    public String apiCallTwo(String input) {
        return getStringHttpResponse(GET_URL_SLEEP_Next,input);
    }

    @Override
    public String apiCallThree(String input) {
        return getStringHttpResponse(GET_URL_SLEEP,input);
    }

    @Override
    public String apiCallFour(String input) {
        return getStringHttpResponse(GET_URL_SLEEP_Next,input);
    }

    @Override
    public String apiCallFive(String input) {
        return getStringHttpResponse(GET_URL_SLEEP_Next,input);
    }

    @Override
    public String apiCallSix(String input) {
        return getStringHttpResponse(GET_URL_SLEEP,input);
    }

    @Override
    public String apiCallSeven(String input) {
        return getStringHttpResponse(GET_URL_SLEEP_Next,input);
    }

    @Override
    public String apiCallEight(String input) {
        return getStringHttpResponse(GET_URL_SLEEP,input);
    }

    @Override
    public String apiCallNine(String input) {
        return getStringHttpResponse(GET_URL_SLEEP_Next,input);
    }

    @Override
    public String apiCallTen(String input) {
        return getStringHttpResponse(GET_URL_SLEEP,input);
    }


    public static String getStringHttpResponse(String url, String val) {
        HttpClient client = HttpClient.newHttpClient();
        String formatURL = String.format(url, val);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(formatURL))
                .build();

        try {
            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return formatURL;
    }
}
