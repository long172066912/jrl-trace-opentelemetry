package org.jrl.trace.otel;

import org.jrl.trace.JrlSpan;
import org.jrl.trace.JrlSpanContext;
import org.jrl.trace.JrlTracer;
import org.jrl.trace.model.OtelContants;
import org.jrl.trace.utils.JrlCallable;
import org.jrl.trace.utils.OtelIdGeneratorUtils;
import io.opentelemetry.api.trace.*;
import io.opentelemetry.context.Context;
import io.opentelemetry.context.Scope;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import java.util.Map;

/**
 * @author JerryLong
 * @version V1.0
 */
public class JrlOtelTracer implements JrlTracer {

    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(JrlOtelTracer.class);

    private Tracer tracer;

    public JrlOtelTracer(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public JrlSpan buildSpan(String spanName) {
        return new JrlOtelSpan(tracer.spanBuilder(spanName).startSpan());
    }

    @Override
    public <V> V span(String spanName, String newTraceId, JrlCallable<V> callable) throws Throwable {
        return span(spanName, newTraceId, OtelIdGeneratorUtils.generateSpanId(), null, callable);
    }

    @Override
    public <V> V span(String spanName, String newTraceId, Map<String, String> tags, JrlCallable<V> callable) throws Throwable {
        return span(spanName, newTraceId, OtelIdGeneratorUtils.generateSpanId(), tags, callable);
    }

    @Override
    public <V> V span(String spanName, String newTraceId, String newSpanId, JrlCallable<V> callable) throws Throwable {
        return span(spanName, newTraceId, newSpanId, null, callable);
    }

    @Override
    public <V> V span(String spanName, String newTraceId, String newSpanId, Map<String, String> tags, JrlCallable<V> callable) throws Throwable {
        try {
            if (StringUtils.isBlank(newTraceId) || OtelContants.START_TRACE_ID.equals(newTraceId) || OtelContants.NULL_TRACE_ID.equals(newTraceId)) {
                newTraceId = OtelIdGeneratorUtils.generateTraceId();
            }
            if (StringUtils.isBlank(newSpanId) || OtelContants.START_SPAN_ID.equals(newSpanId)) {
                newSpanId = OtelIdGeneratorUtils.generateSpanId();
            }
            final SpanContext spanContext = SpanContext.createFromRemoteParent(newTraceId, newSpanId, TraceFlags.getSampled(), TraceState.getDefault());
            // Start the span using the created SpanContext
            try (Scope scope = Context.current().with(Span.wrap(spanContext)).makeCurrent()) {
                return span(tracer.spanBuilder(spanName).startSpan(), tags, callable);
            }
        } catch (JrlCallable.JrlSpanBusinessException businessException) {
            throw businessException.getThrowable();
        } catch (Throwable e) {
            LOGGER.warn("JrlOtelTracer span error ! spanName : {} , traceId : {} , spanId : {}", spanName, newTraceId, newSpanId, e);
            return callable.call();
        }
    }

    @Override
    public <V> V span(String spanName, JrlCallable<V> callable) throws Throwable {
        return span(spanName, Span.current().getSpanContext().getTraceId(), Span.current().getSpanContext().getSpanId(), null, callable);
    }

    @Override
    public <V> V span(String spanName, Map<String, String> tags, JrlCallable<V> callable) throws Throwable {
        return span(spanName, Span.current().getSpanContext().getTraceId(), Span.current().getSpanContext().getSpanId(), tags, callable);
    }

    @Override
    public <V> V spanWithW3cParent(String spanName, String w3cTraceParent, JrlCallable<V> callable) throws Throwable {
        //解析w3cTraceParent，获取traceId与spanId
        String[] parts = w3cTraceParent.split(JrlSpanContext.TRACEPARENT_DELIMITER);
        if (parts.length >= 4) {
            String traceId = parts[1];
            String spanId = parts[2];
            return span(spanName, traceId, spanId, callable);
        }
        return span(spanName, callable);
    }

    @Override
    public <V> V spanWithW3cParent(String spanName, String w3cTraceParent, Map<String, String> tags, JrlCallable<V> callable) throws Throwable {
        //解析w3cTraceParent，获取traceId与spanId
        String[] parts = w3cTraceParent.split(JrlSpanContext.TRACEPARENT_DELIMITER);
        if (parts.length >= 4) {
            String traceId = parts[1];
            String spanId = parts[2];
            return span(spanName, traceId, spanId, tags, callable);
        }
        return span(spanName, Span.current().getSpanContext().getTraceId(), Span.current().getSpanContext().getSpanId(), tags, callable);
    }

    private <V> V span(Span span, Map<String, String> tags, JrlCallable<V> callable) throws Throwable {
        if (null != tags && tags.size() > 0) {
            tags.forEach(span::setAttribute);
        }
        try (Scope spanScope = Context.current().with(span).makeCurrent()) {
            return callable.call0();
        } catch (Throwable e) {
            span.setStatus(StatusCode.ERROR);
            span.recordException(e);
            throw e;
        } finally {
            span.end();
        }
    }
}
