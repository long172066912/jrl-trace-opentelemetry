package org.jrl.trace.otel;

import org.jrl.trace.JrlSpan;
import org.jrl.trace.model.JrlTraceStatus;
import org.jrl.trace.model.JrlTraceStatusCode;
import org.jrl.trace.model.OtelContants;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;

/**
 * @author JerryLong
 * @version V1.0
 */
public class JrlOtelSpan implements JrlSpan {

    private Span span;

    public JrlOtelSpan(Span span) {
        this.span = span;
    }

    @Override
    public String getTraceId() {
        final String traceId = span.getSpanContext().getTraceId();
        if (!OtelContants.START_TRACE_ID.equals(traceId)) {
            return traceId;
        }
        return null;
    }

    @Override
    public String getSpanId() {
        return span.getSpanContext().getSpanId();
    }

    @Override
    public void addTag(String key, String value) {
        span.setAttribute(key, value);
    }

    @Override
    public void setStatus(JrlTraceStatus jrlTraceStatus) {
        span.setStatus(JrlTraceStatusCode.OK.equals(jrlTraceStatus.getCode()) ? StatusCode.OK : StatusCode.ERROR, jrlTraceStatus.getDesc());
    }

    @Override
    public void end() {
        span.end();
    }

    public Span getSpan() {
        return span;
    }
}
