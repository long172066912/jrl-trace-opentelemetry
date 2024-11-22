package org.jrl.trace;

import org.jrl.trace.model.JrlTraceConstants;
import org.jrl.trace.utils.JrlTracerUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JrlTracerTest {

    @Test
    public void test() {
        final JrlTracer tracer = JrlTracerUtil.trace("testTracer");
        Assertions.assertNotNull(tracer);
        final JrlSpan span = tracer.buildSpan("testSpan");
        span.end();
        Assertions.assertNotNull(span);
        Assertions.assertEquals(span.getTraceId(), JrlTracerUtil.getTraceId());
        Assertions.assertEquals(span.getTraceId(), JrlTraceConstants.DEFAULT_TRACE_ID);
    }
}