package example.temporal.fileprocessor;

import io.temporal.activity.ActivityOptions;
import io.temporal.workflow.Workflow;
import org.slf4j.Logger;

import java.time.Duration;

public class FileProcessorWorkFlowImpl implements FileProcessorWorkFlow {

    private static Logger logger = Workflow.getLogger(FileProcessorWorkFlowImpl.class);

    private final FileProcessorActivity activity =
            Workflow.newActivityStub(FileProcessorActivity.class,
                    ActivityOptions.newBuilder()
                            .setStartToCloseTimeout(Duration.ofMinutes(5))
                            .setScheduleToCloseTimeout(Duration.ofMinutes(2))
                            .build());

    @Override
    public void fileProcessorRun(String filePath, String uploadUrl) {
        String getFileId = activity.uploadFile(filePath,uploadUrl);
        logger.info("File {} upload completed file ID {}", filePath, getFileId);
        String downloadUrl = String.format("http://localhost:8175/handle/download/%s/content",getFileId);
        activity.downloadFile(downloadUrl);

    }
}
