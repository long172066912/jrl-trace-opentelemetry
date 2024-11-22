package org.jrl.trace.utils;

import org.jrl.trace.JrlSpan;
import org.jrl.trace.JrlTracer;
import org.jrl.trace.spi.JrlTracerBuilder;
import org.jrl.trace.spi.JrlTracerHandler;

import java.util.Map;

/**
 * trace相关util
 *
 * @author JerryLong
 * @version V1.0
 */
public class JrlTracerUtil {
    /**
     * SPI实现注入
     */
    private static JrlTracerHandler tracerHandler = JrlClassSpiUtil.getInstanceOrDefault(JrlTracerHandler.class, JrlTracerHandler.DefaultTracerHandler::new);
    private static JrlTracerBuilder tracerBuilder = JrlClassSpiUtil.getInstanceOrDefault(JrlTracerBuilder.class, JrlTracerBuilder.DefaultTracerBuilder::new);

    /**
     * 获取 baggage
     *
     * @param key
     * @return
     */
    public static String getBaggage(String key) {
        return tracerHandler.getSpanContext().getBaggage(key);
    }

    /**
     * 获取 baggage
     *
     * @param key
     * @param value
     */
    public static void setBaggage(String key, String value) {
        tracerHandler.getSpanContext().setBaggage(key, value);
    }

    /**
     * 获取TraceId
     *
     * @return
     */
    public static String getTraceId() {
        return tracerHandler.getSpanContext().getTraceId();
    }

    /**
     * 设置TraceId
     *
     * @param traceId
     * @param spanId
     */
    public static void setTraceContext(String traceId, String spanId) {
        tracerHandler.getSpanContext().setTraceContext(traceId, spanId);
    }

    /**
     * 获取符合W3C格式的traceparent 00-traceId-spanId-01
     *
     * @return
     */
    public static String getW3cTraceParent() {
        return tracerHandler.getSpanContext().getW3cTraceParent();
    }

    /**
     * 构建tracer
     *
     * @param name tracer name，可以是组件名称、业务场景
     * @return
     */
    public static JrlTracer trace(String name) {
        return tracerBuilder.buildTracer(name);
    }

    /**
     * 获取当前运转的span
     *
     * @return
     */
    public static JrlSpan getCurrentSpan() {
        return tracerHandler.getSpanContext().getCurrentSpan();
    }

    /**
     * 构建span
     *
     * @param tracer   tracer
     * @param spanName span名称，可以是方法名称、接口等
     * @param callable 执行逻辑
     * @param <V>      返回类型
     * @return callable 执行结果
     * @throws Exception callable执行异常处理
     */
    public static <V> V span(JrlTracer tracer, String spanName, JrlCallable<V> callable) throws Throwable {
        return tracer.span(spanName, callable);
    }

    /**
     * 构建span
     *
     * @param tracer   tracer
     * @param spanName span名称，可以是方法名称、接口等
     * @param tags     标签信息
     * @param callable 执行逻辑
     * @param <V>      返回类型
     * @return callable 执行结果
     * @throws Exception callable执行异常处理
     */
    public static <V> V span(JrlTracer tracer, String spanName, Map<String, String> tags, JrlCallable<V> callable) throws Throwable {
        return tracer.span(spanName, tags, callable);
    }

    /**
     * 构建span
     *
     * @param tracer   tracer
     * @param spanName span名称，可以是方法名称、接口等
     * @param traceId  如果是otel实现，则必须符合Otel规范
     * @param callable 执行逻辑
     * @param <V>      返回类型
     * @return callable 执行结果
     * @throws Exception callable执行异常处理
     */
    public static <V> V span(JrlTracer tracer, String spanName, String traceId, JrlCallable<V> callable) throws Throwable {
        return tracer.span(spanName, traceId, callable);
    }

    /**
     * 构建span
     *
     * @param tracer   tracer
     * @param spanName span名称，可以是方法名称、接口等
     * @param traceId  如果是otel实现，则必须符合Otel规范
     * @param spanId
     * @param callable 执行逻辑
     * @param <V>      返回类型
     * @return callable 执行结果
     * @throws Exception callable执行异常处理
     */
    public static <V> V span(JrlTracer tracer, String spanName, String traceId, String spanId, JrlCallable<V> callable) throws Throwable {
        return tracer.span(spanName, traceId, spanId, callable);
    }

    /**
     * 构建span
     *
     * @param tracer   tracer
     * @param spanName span名称，可以是方法名称、接口等
     * @param traceId  如果是otel实现，则必须符合Otel规范
     * @param spanId 如果是otel实现，则必须符合Otel规范，可以传null
     * @param tags
     * @param callable 执行逻辑
     * @param <V>      返回类型
     * @return callable 执行结果
     * @throws Exception callable执行异常处理
     */
    public static <V> V span(JrlTracer tracer, String spanName, String traceId, String spanId, Map<String, String> tags, JrlCallable<V> callable) throws Throwable {
        return tracer.span(spanName, traceId, spanId, tags, callable);
    }

    /**
     * 构建span
     *
     * @param tracer   tracer
     * @param spanName span名称，可以是方法名称、接口等
     * @param w3cTraceParent  符合w3c的traceParent 00-traceId-spanId-01
     * @param callable 执行逻辑
     * @param <V>      返回类型
     * @return callable 执行结果
     * @throws Exception callable执行异常处理
     */
    public static <V> V spanWithW3cParent(JrlTracer tracer, String spanName, String w3cTraceParent, JrlCallable<V> callable) throws Throwable {
        return tracer.spanWithW3cParent(spanName, w3cTraceParent, callable);
    }


    /**
     * 构建span
     *
     * @param tracer   tracer
     * @param spanName span名称，可以是方法名称、接口等
     * @param w3cTraceParent  符合w3c的traceParent 00-traceId-spanId-01
     * @param tags
     * @param callable 执行逻辑
     * @param <V>      返回类型
     * @return callable 执行结果
     * @throws Exception callable执行异常处理
     */
    public static <V> V spanWithW3cParent(JrlTracer tracer, String spanName, String w3cTraceParent, Map<String, String> tags, JrlCallable<V> callable) throws Throwable {
        return tracer.spanWithW3cParent(spanName, w3cTraceParent, tags, callable);
    }
}
