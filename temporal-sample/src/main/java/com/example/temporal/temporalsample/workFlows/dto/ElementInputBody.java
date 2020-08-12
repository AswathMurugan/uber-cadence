package com.example.temporal.temporalsample.workFlows.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ElementInputBody<R,S> {

    private String elementType;
    private int sequenceID;
    private String uuid;
    private String componentType;
    private R config;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private MachineInfo[] machineInfo;// Added for SBE
    private String reqURI;// Added for SBE
    private String version; // bot version check
    private List<ElementIterationInput<S>> input;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int tenantId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userName;

    private HashMap<String, List<String>> keyedAttrs;

    private String taskId;
    private String nodeName;

    private String release;

    private String taskName;
    private String projectAppgroup;
    private String releaseApp;

}
