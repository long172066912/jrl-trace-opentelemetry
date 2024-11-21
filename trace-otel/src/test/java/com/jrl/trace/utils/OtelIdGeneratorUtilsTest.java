package com.jrl.trace.utils;

import io.opentelemetry.api.trace.SpanId;
import io.opentelemetry.api.trace.TraceId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OtelIdGeneratorUtilsTest {

    @Test
    void testGenerateTraceId() {
        Assertions.assertTrue(TraceId.isValid(OtelIdGeneratorUtils.generateTraceId()));
    }

    @Test
    void testGenerateSpanId() {
        Assertions.assertTrue(SpanId.isValid(OtelIdGeneratorUtils.generateSpanId()));
    }
}