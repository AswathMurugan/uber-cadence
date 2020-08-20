package example.temporal.fileprocessor;

import com.google.common.io.Files;
import com.google.common.io.Resources;
import io.temporal.workflow.Workflow;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

public class FileProcessorActivityImpl implements  FileProcessorActivity{

    private static Logger logger = Workflow.getLogger(FileProcessorActivityImpl.class);

    private  static final String uploadUrl ="http://127.0.0.1:8175/uploadFile";
    private  static RestTemplate restTemplate = new RestTemplate();
    //{"fID":"1f94bf39-422e-46ee-9a08-7355dd373496"}

    @Override
    public String uploadFile(String localFileName, String url) {
        File file = new File(localFileName);
        if (!file.isFile()) {
            throw new IllegalArgumentException("Invalid file type: " + file);
        }
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        FileBody fileBody = new FileBody(file);
        builder.addPart("file", fileBody);
        logger.info("Uploading file........");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        org.springframework.util.MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new FileSystemResource(file));

        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate
                .postForEntity(url, requestEntity, String.class);
        String responseBody = response.getBody();
        //{"fID":"1f94bf39-422e-46ee-9a08-7355dd373496"}
        JSONObject inputObject = new JSONObject(responseBody);
        return inputObject.get("fID").toString();
    }

    @Override
    public String downloadFile(String downloadUrl) {
        try{
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity entity = new HttpEntity(headers);
            ResponseEntity<byte[]> rspEntity =
                    restTemplate.exchange(downloadUrl, HttpMethod.GET, entity,byte[].class);
            logger.info("Downloading....");
            if (rspEntity.getStatusCode() == HttpStatus.OK) {
                List<String> fileNames = rspEntity.getHeaders().get("fileName");
                String filename = fileNames.get(0);
                byte[] fileByte = rspEntity.getBody();
                File destination = new File(Files.createTempDir(), filename);
                Files.write(fileByte, destination);
                logger.info(
                        "download activity: downloaded from " + downloadUrl + " to " + destination.getAbsolutePath());
                return destination.getAbsolutePath();
            }

        }catch (IOException e){
            throw Workflow.wrap(e);
        }
        return downloadUrl;
    }
}
