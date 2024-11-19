package com.jrl.trace.model;

/**
* @author JerryLong
* @version V1.0
*/
public class JrlTraceStatus {
    private JrlTraceStatusCode code;
    private String desc;

    public JrlTraceStatus(JrlTraceStatusCode code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public JrlTraceStatusCode getCode() {
        return code;
    }

    public void setCode(JrlTraceStatusCode code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
