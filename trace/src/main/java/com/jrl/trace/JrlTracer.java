package com.jrl.trace;

import com.jrl.trace.utils.JrlCallable;

import java.util.Map;

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

    /**
     * 创建span-指定traceId
     *
     * @param spanName
     * @param newTraceId 如果是otel实现，则必须符合Otel规范
     * @param callable
     * @param <V>
     * @return
     * @throws Exception
     */
    <V> V span(String spanName, String newTraceId, JrlCallable<V> callable) throws Throwable;

    /**
     * 创建span-指定traceId
     *
     * @param spanName
     * @param newTraceId 如果是otel实现，则必须符合Otel规范
     * @param tags
     * @param callable
     * @param <V>
     * @return
     * @throws Exception
     */
    <V> V span(String spanName, String newTraceId, Map<String, String> tags, JrlCallable<V> callable) throws Throwable;

    /**
     * 创建span-指定traceId
     *
     * @param spanName
     * @param newTraceId 如果是otel实现，则必须符合Otel规范
     * @param newSpanId 如果是otel实现，则必须符合Otel规范
     * @param callable
     * @param <V>
     * @return
     * @throws Exception
     */
    <V> V span(String spanName, String newTraceId, String newSpanId, JrlCallable<V> callable) throws Throwable;

    /**
     * 创建span-指定traceId
     *
     * @param spanName
     * @param newTraceId 如果是otel实现，则必须符合Otel规范
     * @param newSpanId 如果是otel实现，则必须符合Otel规范
     * @param tags
     * @param callable
     * @param <V>
     * @return
     * @throws Exception
     */
    <V> V span(String spanName, String newTraceId, String newSpanId, Map<String, String> tags, JrlCallable<V> callable) throws Throwable;

    /**
     * 创建span
     *
     * @param spanName
     * @param callable
     * @param <V>
     * @return
     * @throws Exception
     */
    <V> V span(String spanName, JrlCallable<V> callable) throws Throwable;

    /**
     * 创建span
     *
     * @param spanName
     * @param callable
     * @param <V>
     * @return
     * @throws Exception
     */
    <V> V span(String spanName, Map<String, String> tags, JrlCallable<V> callable) throws Throwable;

    /**
     * 创建span
     * @param spanName
     * @param w3cTraceParent
     * @param callable
     * @param <V>
     * @return
     */
    <V> V spanWithW3cParent(String spanName, String w3cTraceParent, JrlCallable<V> callable) throws Throwable;

    /**
     * 创建span
     * @param spanName
     * @param w3cTraceParent
     * @param tags
     * @param callable
     * @param <V>
     * @return
     */
    <V> V spanWithW3cParent(String spanName, String w3cTraceParent, Map<String, String> tags, JrlCallable<V> callable) throws Throwable;
}
