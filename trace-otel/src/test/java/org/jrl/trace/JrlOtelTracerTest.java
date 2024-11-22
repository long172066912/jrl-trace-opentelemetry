package org.jrl.trace;

import org.jrl.trace.utils.JrlTracerUtil;
import org.jrl.trace.utils.OtelIdGeneratorUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JrlOtelTracerTest {

    @Test
    public void test() {
        final JrlTracer tracer = JrlTracerUtil.trace("testTracer");
        Assertions.assertNotNull(tracer);
        final JrlSpan span = tracer.buildSpan("testSpan");
        span.end();
        Assertions.assertNotNull(span);
        Assertions.assertEquals(span.getTraceId(), JrlTracerUtil.getTraceId());
    }

    @Test
    public void testCallable() throws Throwable {
        final JrlTracer tracer = JrlTracerUtil.trace("testTracer");
        Assertions.assertNotNull(tracer);
        final String v = JrlTracerUtil.span(tracer, "testSpan", () -> "a");
        Assertions.assertEquals(v, "a");
    }

    @Test
    public void testCallable1() throws Throwable {
        final JrlTracer tracer = JrlTracerUtil.trace("testTracer");
        Assertions.assertNotNull(tracer);
        final String v = JrlTracerUtil.span(tracer, "testSpan", "123", "123", () -> "a");
        Assertions.assertEquals(v, "a");
    }

    @Test
    public void testCallable2() {
        final JrlTracer tracer = JrlTracerUtil.trace("testTracer");
        Assertions.assertNotNull(tracer);
        Assertions.assertThrows(RuntimeException.class, () -> JrlTracerUtil.span(tracer, "testSpan", "123", "123", () -> {
            throw new RuntimeException("a");
        }), "a");
    }

    @Test
    public void testCallable3() throws Throwable {
        final JrlTracer tracer = JrlTracerUtil.trace("testTracer");
        final String w3cTraceParent = JrlTracerUtil.getW3cTraceParent();
        Assertions.assertNotNull(w3cTraceParent);
        final String[] split = w3cTraceParent.split(JrlSpanContext.TRACEPARENT_DELIMITER);
        Assertions.assertEquals(split.length, 4);
        final String v = JrlTracerUtil.spanWithW3cParent(tracer, "testSpan", w3cTraceParent, () -> "a");
        Assertions.assertEquals(v, "a");
    }

    @Test
    public void testBaggage() {
        String key = "baggageTest";
        Assertions.assertNull(JrlTracerUtil.getBaggage(key));
        JrlTracerUtil.setBaggage(key, "123");
        Assertions.assertEquals(JrlTracerUtil.getBaggage(key), "123");
        JrlTracerUtil.setBaggage(key + "a", "123");
        Assertions.assertEquals(JrlTracerUtil.getBaggage(key), "123");
        Assertions.assertEquals(JrlTracerUtil.getBaggage(key + "a"), "123");
    }

    @Test
    public void testSetTraceContext() {
        Assertions.assertNull(JrlTracerUtil.getTraceId());
        final String traceId = OtelIdGeneratorUtils.generateTraceId();
        JrlTracerUtil.setTraceContext(traceId, OtelIdGeneratorUtils.generateSpanId());
        Assertions.assertEquals(JrlTracerUtil.getTraceId(), traceId);
    }
}