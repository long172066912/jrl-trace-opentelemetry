package org.jrl.trace.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * SPI帮助类
 * @author JerryLong
 * @version V1.0
 */
public class JrlClassSpiUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(JrlClassSpiUtil.class);

    private static Map<Class<?>, Object> objectInstanceMap = new ConcurrentHashMap<>();

    public static <T> T getInstanceOrDefault(Class<T> clazz, Supplier<T> def) {
        try {
            final Iterator<? extends T> iterator = ServiceLoader.load(clazz).iterator();
            if (iterator.hasNext()) {
                final T next = iterator.next();
                return (T) objectInstanceMap.computeIfAbsent(next.getClass(), e -> next);
            } else {
                return def.get();
            }
        } catch (Exception e) {
            LOGGER.error(" SdkSpiUtil fail to load spi , class : {}", clazz.getSimpleName(), e);
            return def.get();
        }
    }
}