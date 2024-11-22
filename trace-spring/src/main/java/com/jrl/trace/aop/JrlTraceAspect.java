package com.jrl.trace.aop;

import com.jrl.trace.JrlTracer;
import com.jrl.trace.annotations.JrlTrace;
import com.jrl.trace.utils.ExpressionEvaluator;
import com.jrl.trace.utils.JrlTracerUtil;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * JrlTrace 处理切面
 *
 * @author JerryLong
 * @version V1.0
 */
@Aspect
public class JrlTraceAspect {

    public static final String SPEL_MATCHES = "^#.*.$";
    private static Logger LOGGER = org.slf4j.LoggerFactory.getLogger(JrlTraceAspect.class);

    private static final String JRL_ASPECT_KEY = "Business@JrlTrace";

    private static JrlTracer tracer = JrlTracerUtil.trace(JRL_ASPECT_KEY);

    @Around("@annotation(com.jrl.trace.annotations.JrlTrace)")
    public Object jrlTraceAround(ProceedingJoinPoint pjp) throws Throwable {
        Map<String, String> tagKvs = null;
        String spanName = null;
        try {
            Method method = ((MethodSignature) pjp.getSignature()).getMethod();
            //获取注解信息
            final JrlTrace jrlTrace = method.getAnnotation(JrlTrace.class);
            spanName = StringUtils.isBlank(jrlTrace.value()) ? method.getName() : jrlTrace.value();
            String[] tags = jrlTrace.tags();
            if (tags.length > 0) {
                tagKvs = new HashMap<>(tags.length);
                for (int i = 0; i < tags.length; i++) {
                    String tag = tags[i];
                    i++;
                    String v = tags[i];
                    //使用spel表达式
                    if (v.matches(SPEL_MATCHES)) {
                        v = ExpressionEvaluator.getConditionValue(pjp, v, String.class);
                    }
                    tagKvs.put(tag, v);
                }
            }
        } catch (Throwable e) {
            LOGGER.warn("JrlTraceAspect handle error !", e);
        }
        //执行方法
        return JrlTracerUtil.span(tracer, spanName, pjp::proceed);
    }
}
