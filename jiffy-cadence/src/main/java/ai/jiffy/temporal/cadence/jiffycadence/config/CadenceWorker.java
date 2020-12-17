package ai.jiffy.temporal.cadence.jiffycadence.config;

import ai.jiffy.temporal.cadence.jiffycadence.Hello.HelloActivityImpl;
import ai.jiffy.temporal.cadence.jiffycadence.Hello.HelloWorkFlowImpl;
import ai.jiffy.temporal.cadence.jiffycadence.asyncTest.AsyncActivityImp;
import ai.jiffy.temporal.cadence.jiffycadence.asyncTest.AsyncActivityWorkFlowImpl;
import ai.jiffy.temporal.cadence.jiffycadence.botProcessor.ProcessorActivityImpl;
import ai.jiffy.temporal.cadence.jiffycadence.botProcessor.ProcessorWorkFlow;
import ai.jiffy.temporal.cadence.jiffycadence.botProcessor.ProcessorWorkFlowImpl;
import ai.jiffy.temporal.cadence.jiffycadence.customDSL.DSLActivityImpl;
import ai.jiffy.temporal.cadence.jiffycadence.customDSL.DSLWorkFlowImpl;
import ai.jiffy.temporal.cadence.jiffycadence.dsl.InterpreterImpl;
import ai.jiffy.temporal.cadence.jiffycadence.dsl.InterpreterWorkflowImpl;
import ai.jiffy.temporal.cadence.jiffycadence.loadtest.LoadTestActivityImpl;
import ai.jiffy.temporal.cadence.jiffycadence.loadtest.LoadTestWorkFlowImpl;
import ai.jiffy.temporal.cadence.jiffycadence.sampleFlows.randomNumber.RandomNumberActivityImpl;
import ai.jiffy.temporal.cadence.jiffycadence.sampleFlows.randomNumber.RandomNumberActivityWorkFlowImpl;
import io.temporal.client.ActivityCompletionClient;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import io.temporal.workflow.Workflow;
import org.json.JSONException;
import org.slf4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ai.jiffy.temporal.cadence.jiffycadence.commons.StringConstant.*;

@Configuration
public class CadenceWorker {


    private static Logger logger = Workflow.getLogger(CadenceWorker.class);

    // Start a worker that hosts the workflow implementation.
    public static WorkflowServiceStubs service = WorkflowServiceStubs.newInstance();
    public static WorkflowClient client = WorkflowClient.newInstance(service);

    @Bean
    public void worker() throws JSONException {
        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker(TASK_QUEUE);

        // Activities are stateless and thread safe. So a shared instance is used.
        Map<String, Map<String, String>> definitions = new HashMap<>();
        Map<String, String> definition1 = getDefinition1();
        definitions.put("type1", definition1);

        worker.registerWorkflowImplementationTypes(ProcessorWorkFlowImpl.class);
        worker.registerActivitiesImplementations(new ProcessorActivityImpl());
        //worker.registerWorkflowImplementationTypes(LoadTestWorkFlowImpl.class);
        //worker.registerActivitiesImplementations(new LoadTestActivityImpl());

        /*worker.registerWorkflowImplementationTypes(HelloWorkFlowImpl.class,
                InterpreterWorkflowImpl.class, DSLWorkFlowImpl.class,
                RandomNumberActivityWorkFlowImpl.class,
                AsyncActivityWorkFlowImpl.class
                );
        ActivityCompletionClient completionClient = client.newActivityCompletionClient();
        worker.registerActivitiesImplementations(new HelloActivityImpl(), new InterpreterImpl(definitions),
                new DSLActivityImpl(DSL_JSON_INPUT),
                new RandomNumberActivityImpl(),
                new AsyncActivityImp(completionClient));*/
        factory.start();
        logger.info("Thread..{} Starting the workflow task name:..{}",Thread.currentThread().getName(), TASK_QUEUE);
    }

    private static Map<String, String> getDefinition1() {
        Map<String, String> definition1 = new HashMap<>();
        List<String> sequence =
                Arrays.asList(
                        "Activity::ApiCallOneI",
                        "ApiCallTwoI",
                        "ApiCallThreeI",
                        "ApiCallFourI",
                        "ApiCallFiveI",
                        "ApiCallSixI",
                        "ApiCallSevenI",
                        "ApiCallEightI",
                        "ApiCallNineI",
                        "ApiCallTenI");
        definition1.put("init", sequence.get(0));

        for (int i = 0; i < sequence.size() - 1; i++) {
            definition1.put(sequence.get(i), sequence.get(i + 1));
        }
        definition1.put(sequence.get(sequence.size() - 1), null);
        return definition1;
    }


}
