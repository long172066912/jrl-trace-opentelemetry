package org.jrl.trace.spi;


import org.jrl.trace.JrlSpan;
import org.jrl.trace.JrlSpanContext;
import org.jrl.trace.JrlTracer;

/**
 * @author JerryLong
 * @version V1.0
 */
public interface JrlTracerHandler {
    /**
     * 获取content
     *
     * @return
     */
    JrlSpanContext getSpanContext();

    class DefaultTracerHandler implements JrlTracerHandler {

        private static final JrlTracer DEFAULT_TRACER = new JrlTracerBuilder.DefaultTracerBuilder().buildTracer(null);

        private static final JrlSpanContext DEFAULT_SPAN_CONTEXT = new JrlSpanContext() {
            @Override
            public JrlSpan getCurrentSpan() {
                return DEFAULT_TRACER.buildSpan(null);
            }

            @Override
            public String getTraceId() {
                return DEFAULT_TRACER.buildSpan(null).getTraceId();
            }

            @Override
            public String getW3cTraceParent() {
                return VERSION_00 + TRACEPARENT_DELIMITER + getCurrentSpan().getTraceId() + TRACEPARENT_DELIMITER + getCurrentSpan().getSpanId() + TRACEPARENT_DELIMITER + "01";
            }

            @Override
            public String getBaggage(String key) {
                return null;
            }

            @Override
            public void setBaggage(String key, String value) {

            }

            @Override
            public void setTraceContext(String traceId, String spanId) {

            }
        };

        @Override
        public JrlSpanContext getSpanContext() {
            return DEFAULT_SPAN_CONTEXT;
        }
    }
}
