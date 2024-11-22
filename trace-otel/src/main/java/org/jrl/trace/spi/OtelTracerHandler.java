package org.jrl.trace.spi;

import org.jrl.trace.JrlSpan;
import org.jrl.trace.JrlSpanContext;
import org.jrl.trace.model.OtelContants;
import org.jrl.trace.otel.JrlOtelSpan;
import io.opentelemetry.api.baggage.Baggage;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.SpanContext;
import io.opentelemetry.api.trace.TraceFlags;
import io.opentelemetry.api.trace.TraceState;
import io.opentelemetry.context.Context;

/**
 * @author JerryLong
 * @version V1.0
 */
public class OtelTracerHandler implements JrlTracerHandler {

    private static final JrlSpanContext DEFAULT_CONTEXT = new DefaultSpanContext();

    @Override
    public JrlSpanContext getSpanContext() {
        return DEFAULT_CONTEXT;
    }

    private static class DefaultSpanContext implements JrlSpanContext {
        private static final String SAMPLED_VERSION = "01";
        private static final String NO_SAMPLED_VERSION = "00";

        @Override
        public JrlSpan getCurrentSpan() {
            return new JrlOtelSpan(Span.current());
        }

        @Override
        public String getTraceId() {
            final String traceId = Span.current().getSpanContext().getTraceId();
            if (!OtelContants.START_TRACE_ID.equals(traceId)) {
                return traceId;
            }
            return null;
        }

        @Override
        public String getW3cTraceParent() {
            return VERSION_00 + TRACEPARENT_DELIMITER + getTraceId() + TRACEPARENT_DELIMITER + Span.current().getSpanContext().getSpanId() + TRACEPARENT_DELIMITER + (Span.current().getSpanContext().isSampled() ? SAMPLED_VERSION : NO_SAMPLED_VERSION);
        }

        @Override
        public String getBaggage(String key) {
            return Baggage.current().getEntryValue(key);
        }

        @Override
        public void setBaggage(String key, String value) {
            // 将数据写入OpenTelemetry的Baggage中
            Baggage.current().toBuilder().put(key, value).build().storeInContext(Context.current()).makeCurrent();
        }

        @Override
        public void setTraceContext(String traceId, String spanId) {
            final SpanContext spanContext = SpanContext.createFromRemoteParent(traceId, spanId, TraceFlags.getSampled(), TraceState.getDefault());
            Context.current().with(Span.wrap(spanContext)).makeCurrent();
        }
    }
}
