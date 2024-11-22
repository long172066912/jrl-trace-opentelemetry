package org.jrl.trace.annotations;

import java.lang.annotation.*;

/**
 * 标注在方法上，增加Span信息
 * @author JerryLong
 * @version V1.0
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JrlTrace {

    /**
     * 名称，如果不设置，则为MethodName
     */
    String value() default "";

    /**
     * 标签信息
     *
     * @return kv结构的数组,例如：{k1,v1,k2,v2}
     */
    String[] tags() default {};
}