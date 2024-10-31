package com.jrl.trace;

/**
 * 链路追踪实体类
 *
 * @author JerryLong
 * @version V1.0
 */
public interface JrlTracer {

    /**
     * 构建Span
     *
     * @param spanName
     * @return
     */
    JrlSpan buildSpan(String spanName);
}
