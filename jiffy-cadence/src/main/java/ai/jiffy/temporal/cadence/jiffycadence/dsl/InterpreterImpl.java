package ai.jiffy.temporal.cadence.jiffycadence.dsl;

import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import static ai.jiffy.temporal.cadence.jiffycadence.commons.StringConstant.GET_URL;

public class InterpreterImpl implements Interpreter {

    private static Logger logger = Workflow.getLogger(InterpreterImpl.class);

    private final Map<String, Map<String, String>> definitions;

    private static String input = "Aswath,one";
    private static String passResult;

    public InterpreterImpl(Map<String, Map<String, String>> definitions) {
        this.definitions = definitions;
    }

    @Override
    public String getNextStep(String workflowType, String lastActivity) {
        logger.info("Work flow typr ..{} and lastActivity ...{}",workflowType, lastActivity);
        Map<String, String> stateTransitions = definitions.get(workflowType);
        String stateTransit = stateTransitions.get(lastActivity);
        logger.info("State Transitions ..{}", stateTransit);
        return stateTransit;
    }

    @Override
    public String apiCallOneI() {
        try {
            passResult = getStringHttpResponse(GET_URL,input).body();
            logger.info("API Call 1 Result ..{}", passResult);
            return passResult;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String apiCallTwoI() {
        try {
            passResult = getStringHttpResponse(GET_URL, passResult+",two").body();
            logger.info("API Call 2 Result ..{}", passResult);
            return passResult;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String apiCallThreeI() {
        try {
            passResult = getStringHttpResponse(GET_URL,passResult+",three").body();
            logger.info("API Call 3 Result ..{}", passResult);
            return passResult;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String apiCallFourI() {
        try {
            passResult = getStringHttpResponse(GET_URL,passResult+",four").body();
            logger.info("API Call 4 Result ..{}", passResult);
            return passResult;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String apiCallFiveI() {
        try {
            passResult = getStringHttpResponse(GET_URL,passResult+",five").body();
            logger.info("API Call 5 Result ..{}", passResult);
            return passResult;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String apiCallSixI() {
        try {
            passResult = getStringHttpResponse(GET_URL,passResult+",six").body();
            logger.info("API Call 6 Result ..{}", passResult);
            return passResult;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String apiCallSevenI() {
        try {
            passResult = getStringHttpResponse(GET_URL,passResult+",seven").body();
            logger.info("API Call 7 Result ..{}", passResult);
            return passResult;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String apiCallEightI() {
        try {
            passResult = getStringHttpResponse(GET_URL,passResult+",eight").body();
            logger.info("API Call 8 Result ..{}", passResult);
            return passResult;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String apiCallNineI() {
        try {
            passResult = getStringHttpResponse(GET_URL,passResult+",nine").body();
            logger.info("API Call 9 Result ..{}", passResult);
            return passResult;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String apiCallTenI() {
        try {
            passResult = getStringHttpResponse(GET_URL,passResult+",ten").body();
            logger.info("API Call 10 Result ..{}", passResult);
            return passResult;
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
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
