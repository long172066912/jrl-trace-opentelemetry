package com.jrl.trace;

/**
 * 链路单节点内容
 *
 * @author JerryLong
 * @version V1.0
 */
public interface JrlSpanContext {
    String VERSION_00 = "00";
    String TRACEPARENT_DELIMITER = "-";

    /**
     * 获取当前的span
     *
     * @return
     */
    JrlSpan getCurrentSpan();

    /**
     * 获取当前的traceId
     *
     * @return
     */
    String getTraceId();

    /**
     * 获取符合W3C格式的traceparent 00-traceId-spanId-01
     *
     * @return
     */
    String getW3cTraceParent();

    /**
     * 获取baggage
     *
     * @param key
     * @return
     */
    String getBaggage(String key);

    /**
     * 设置baggage
     *
     * @param key
     * @param value
     */
    void setBaggage(String key, String value);

    /**
     * 设置traceId和spanId
     * @param traceId
     * @param spanId
     */
    void setTraceContext(String traceId, String spanId);
}
