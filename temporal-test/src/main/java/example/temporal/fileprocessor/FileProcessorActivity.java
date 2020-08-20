package example.temporal.fileprocessor;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

import java.net.URL;

@ActivityInterface
public interface FileProcessorActivity {

    @ActivityMethod
    String uploadFile(String localFileName, String url);

    @ActivityMethod
    String downloadFile(String downloadUrl);
}
