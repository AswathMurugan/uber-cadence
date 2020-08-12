package com.example.temporal.temporalsample.workFlows.dto;

import java.util.HashMap;

public class WorkFlowResponse {

    private String componentType;
    private int sequenceID;
    private String uuid;
    private String elementType;
    private HashMap<String , Object> result;

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public int getSequenceID() {
        return sequenceID;
    }

    public void setSequenceID(int sequenceID) {
        this.sequenceID = sequenceID;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getElementType() {
        return elementType;
    }

    public void setElementType(String elementType) {
        this.elementType = elementType;
    }

    public HashMap<String, Object> getResult() {
        return result;
    }

    public void setResult(HashMap<String, Object> result) {
        this.result = result;
    }
}
