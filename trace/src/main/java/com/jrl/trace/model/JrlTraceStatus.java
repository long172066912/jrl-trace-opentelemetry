package com.jrl.trace.model;

/**
* trace状态，包含code与desc
* @author JerryLong
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
