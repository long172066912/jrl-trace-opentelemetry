package org.jrl.trace.spi;

import org.jrl.trace.JrlScope;
import org.jrl.trace.JrlSpan;
import org.jrl.trace.JrlTracer;
import org.jrl.trace.model.JrlTraceConstants;
import org.jrl.trace.model.JrlTraceStatus;
import org.jrl.trace.utils.JrlCallable;

import java.util.Map;

/**
 * trace相关获取器
 * @author JerryLong
 * @version V1.0
 */
public interface JrlTracerBuilder {
    /**
     * 构建Tracer
     *
     * @return
     */
    JrlTracer buildTracer(String name);

    class DefaultTracerBuilder implements JrlTracerBuilder {

        private static final JrlTracer DEFAULT_TRACER = new JrlTracer() {
            @Override
            public JrlSpan buildSpan(String spanName) {
                return DEFAULT_SPAN;
            }

            @Override
            public <V> V span(String spanName, String newTraceId, JrlCallable<V> callable) throws Throwable {
                return callable.call();
            }

            @Override
            public <V> V span(String spanName, String newTraceId, Map<String, String> tags, JrlCallable<V> callable) throws Throwable {
                return callable.call();
            }

            @Override
            public <V> V span(String spanName, String newTraceId, String newSpanId, JrlCallable<V> callable) throws Throwable {
                return callable.call();
            }

            @Override
            public <V> V span(String spanName, String newTraceId, String newSpanId, Map<String, String> tags, JrlCallable<V> callable) throws Throwable {
                return callable.call();
            }

            @Override
            public <V> V span(String spanName, JrlCallable<V> callable) throws Throwable {
                return callable.call();
            }

            @Override
            public <V> V span(String spanName, Map<String, String> tags, JrlCallable<V> callable) throws Throwable {
                return callable.call();
            }

            @Override
            public <V> V spanWithW3cParent(String spanName, String w3cTraceParent, JrlCallable<V> callable) throws Throwable {
                return callable.call();
            }

            @Override
            public <V> V spanWithW3cParent(String spanName, String w3cTraceParent, Map<String, String> tags, JrlCallable<V> callable) throws Throwable {
                return callable.call();
            }
        };

        private static final JrlSpan DEFAULT_SPAN = new JrlSpan() {

            private final JrlScope DEFAULT_SCOPE = () -> {};

            @Override
            public String getTraceId() {
                return JrlTraceConstants.DEFAULT_TRACE_ID;
            }

            @Override
            public String getSpanId() {
                return JrlTraceConstants.DEFAULT_TRACE_ID;
            }

            @Override
            public void setStatus(JrlTraceStatus jrlTraceStatus) {

            }

            @Override
            public void addTag(String key, String value) {

            }

            @Override
            public void end() {
            }

            @Override
            public JrlScope makeCurrent() {
                return DEFAULT_SCOPE;
            }
        };

        @Override
        public JrlTracer buildTracer(String name) {
            return DEFAULT_TRACER;
        }
    }
}
