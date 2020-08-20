package example.temporal.xmlToJsonTest;

import org.json.JSONObject;
import org.json.XML;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class XJTestActivityImpl implements XJTestActivity {
    @Override
    public String xmlToJsonOne(String data) {
        String xml = null;
        try {
            xml = Files.lines(Paths.get("/home/aswath.murugan/PDFOUTPUT.xml")).collect(Collectors.joining("\n"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        String jsonData = utilXmlToJson(xml);
        System.out.println(jsonData.toString());
        String xmlData = utilJsonToXml(jsonData);
        return "xmlData";
    }

    private String utilJsonToXml(String jsonData){
        JSONObject obj = new JSONObject(jsonData);

        //converting json to xml
        String xml_data = XML.toString(obj);
        return xml_data;
    }

    private String utilXmlToJson(String xmlData){
        //converting xml to json
        JSONObject obj = XML.toJSONObject(xmlData);
        return obj.toString();
    }
}
