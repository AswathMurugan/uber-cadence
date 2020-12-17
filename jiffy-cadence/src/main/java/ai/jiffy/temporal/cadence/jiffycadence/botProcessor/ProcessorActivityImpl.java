package ai.jiffy.temporal.cadence.jiffycadence.botProcessor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ProcessorActivityImpl implements ProcessorActivity {
    public static String GET_URL = "http://localhost:9012/ca/%s";
    @Override
    public String externalApiCall(String input) {
        return getStringHttpResponse(GET_URL,input);
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
