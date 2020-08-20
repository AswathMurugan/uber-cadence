package example.temporal.xmlToJsonTest;


import example.temporal.loadtest.LoadTestWorkFlowImpl;
import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.stream.Collectors;

public class XJTestWorkFlowImpl implements XJTestWorkFlow {

    private static Logger logger = Workflow.getLogger(XJTestWorkFlowImpl.class);

    private final XJTestActivity activity =
            Workflow.newActivityStub(
                    XJTestActivity.class,
                    ActivityOptions.newBuilder()
                            .setStartToCloseTimeout(Duration.ofMinutes(5))
                            .setScheduleToCloseTimeout(Duration.ofHours(1)).build());


    @Override
    public String execute(String xmlData) {
        long time = Workflow.currentTimeMillis();

        String result = activity.xmlToJsonOne("xml");
        //Workflow.sleep(Duration.ofSeconds(25));
        logger.info("WorkFlow id {} ...Execution time in Sec {}",Workflow.getInfo().getWorkflowId(),
                (Workflow.currentTimeMillis() - time)/1000);
        return "result";
    }
}
