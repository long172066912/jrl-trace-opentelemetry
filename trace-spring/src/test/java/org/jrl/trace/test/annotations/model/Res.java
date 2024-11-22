package org.jrl.trace.test.annotations.model;

import java.io.Serializable;

/**
 * @author JerryLong
 * @version V1.0
 */
public class Res implements Serializable {
    private String name;
    private String traceId;

    public Res() {
    }

    public Res(String name, String traceId) {
        this.name = name;
        this.traceId = traceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }
}
