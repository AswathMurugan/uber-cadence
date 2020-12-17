package ai.jiffy.temporal.cadence.jiffycadence.controller;

import ai.jiffy.temporal.cadence.jiffycadence.botProcessor.BotProcessorClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static ai.jiffy.temporal.cadence.jiffycadence.commons.StringConstant.TASK_QUEUE;

@RestController
public class LocalController {

    @RequestMapping("/start/bot/workflow/{workFlowId}/{taskQueue}")
    public boolean startBotProcessor(@PathVariable("workFlowId") String workFlowId,
                                     @PathVariable("taskQueue") String taskQueue) {
        BotProcessorClient.start(workFlowId,TASK_QUEUE);
        return true;
    }
}
