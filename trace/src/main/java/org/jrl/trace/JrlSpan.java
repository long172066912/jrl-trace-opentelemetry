package org.jrl.trace;

import org.jrl.trace.model.JrlTraceStatus;

/**
 * 链路单节点信息
 * @author JerryLong
 * @version V1.0
 */
public interface JrlSpan {
    /**
     * 获取traceId
     *
     * @return
     */
    String getTraceId();

    /**
     * 获取SpanId
     *
     * @return
     */
    String getSpanId();

    /**
     * 设置状态
     *
     * @param jrlTraceStatus
     */
    void setStatus(JrlTraceStatus jrlTraceStatus);

    /**
     * 添加tag
     *
     * @param key
     * @param value
     */
    void addTag(String key, String value);

    /**
     * 结束
     */
    void end();

    /**
     * 设置当前线程为当前span，需要执行scope的close方法
     * 可以的话，建议在try-with-resource中调用，保证try-with-resource正常执行时，会调用close方法
     *
     * @return ZeusScope
     */
    JrlScope makeCurrent();
}
