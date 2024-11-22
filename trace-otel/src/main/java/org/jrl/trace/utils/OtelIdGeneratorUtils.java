package org.jrl.trace.utils;

import io.opentelemetry.api.trace.SpanId;
import io.opentelemetry.api.trace.TraceId;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author JerryLong
 * 用的是 sdk包中 RandomIdGenerator 类的逻辑
 */
public class OtelIdGeneratorUtils {

    private static final long INVALID_ID = 0;

    public static String generateTraceId() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        long idHi = random.nextLong();
        long idLo;
        do {
            idLo = random.nextLong();
        } while (idLo == INVALID_ID);
        return TraceId.fromLongs(idHi, idLo);
    }

    public static String generateSpanId() {
        long id;
        ThreadLocalRandom random = ThreadLocalRandom.current();
        do {
            id = random.nextLong();
        } while (id == INVALID_ID);
        return SpanId.fromLong(id);
    }

    public static void main(String[] args) {
        System.out.println(OtelIdGeneratorUtils.generateSpanId());
        System.out.println(OtelIdGeneratorUtils.generateTraceId());
    }
}
