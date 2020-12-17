package ai.jiffy.temporal.cadence.jiffycadence.Hello;

import ai.jiffy.temporal.cadence.jiffycadence.commons.Utils;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;

import static ai.jiffy.temporal.cadence.jiffycadence.commons.StringConstant.GET_URL;
import static ai.jiffy.temporal.cadence.jiffycadence.commons.StringConstant.GET_URL_SLEEEP;


public class HelloActivityImpl implements HelloActivity {

    private static Logger logger = Workflow.getLogger(HelloActivityImpl.class);



    @Override
    public CompletableFuture<String> apiCallOne(String input) {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        CompletableFuture<ResponseEntity<String>> responseEntityCompletableFuture = Utils.asyncWebClientGet(GET_URL,input);
        responseEntityCompletableFuture.whenCompleteAsync((response, error) -> {
            logger.info("response one..{}",response.getBody());
            completableFuture.completeAsync(()->response.getBody());
        });
        return completableFuture;
    }


    @Override
    public CompletableFuture<String> apiCallTwo(String input) {
        logger.info("We are inside the Activity..");
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        logger.info("Calling external from utils asy mono");
        CompletableFuture<ResponseEntity<String>> responseEntityCompletableFuture = Utils.asyncWebClientGet(GET_URL,input);
        logger.info("Asyn call made wait when complete async");
        responseEntityCompletableFuture.whenCompleteAsync((response, error) -> {
            logger.info("response ..{}",response.getBody());
            completableFuture.completeAsync(()->response.getBody());
        });
        return completableFuture;
    }

    @Override
    public CompletableFuture<String> apiCallThree(String input) {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        CompletableFuture<ResponseEntity<String>> responseEntityCompletableFuture = Utils.asyncWebClientGet(GET_URL_SLEEEP,input);
        responseEntityCompletableFuture.whenCompleteAsync((response, error) -> {
            logger.info("response ..{}",response.getBody());
            completableFuture.completeAsync(()->response.getBody());
        });
        return completableFuture;
    }

    /*@Override
    public String apiCallFour(String input) {
        CompletableFuture<ResponseEntity<String>> completableFuture = Utils.asyncWebClientGet(GET_URL_SLEEEP,input);
        completableFuture.whenCompleteAsync((response, error) -> {
            logger.info("response ..{}",response.getBody());
        });
        return input;
    }

    @Override
    public String apiCallFive(String input) {
        CompletableFuture<ResponseEntity<String>> completableFuture = Utils.asyncWebClientGet(GET_URL_SLEEEP,input);
        completableFuture.whenCompleteAsync((response, error) -> {
            logger.info("response ..{}",response.getBody());
        });
        return input;
    }

    @Override
    public String apiCallSix(String input) {
        CompletableFuture<ResponseEntity<String>> completableFuture = Utils.asyncWebClientGet(GET_URL,input);
        completableFuture.whenCompleteAsync((response, error) -> {
            logger.info("response ..{}",response.getBody());
        });
        return input;
    }

    @Override
    public String apiCallSeven(String input) {
        CompletableFuture<ResponseEntity<String>> completableFuture = Utils.asyncWebClientGet(GET_URL,input);
        completableFuture.whenCompleteAsync((response, error) -> {
            logger.info("response ..{}",response.getBody());
        });
        return input;
    }

    @Override
    public String apiCallEight(String input) {
        CompletableFuture<ResponseEntity<String>> completableFuture = Utils.asyncWebClientGet(GET_URL_SLEEEP,input);
        completableFuture.whenCompleteAsync((response, error) -> {
            logger.info("response ..{}",response.getBody());
        });
        return input;
    }

    @Override
    public String apiCallNine(String input) {
        CompletableFuture<ResponseEntity<String>> completableFuture = Utils.asyncWebClientGet(GET_URL,input);
        completableFuture.whenCompleteAsync((response, error) -> {
            logger.info("response ..{}",response.getBody());
        });
        return input;
    }

    @Override
    public String apiCallTen(String input) {
        CompletableFuture<ResponseEntity<String>> completableFuture = Utils.asyncWebClientGet(GET_URL,input);
        completableFuture.whenCompleteAsync((response, error) -> {
            logger.info("response ..{}",response.getBody());
        });
        return input;
    }*/
}
