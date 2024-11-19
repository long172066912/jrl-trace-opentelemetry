package com.jrl.trace.utils;

/**
 * @author JerryLong
 */
@FunctionalInterface
public interface JrlCallable<V> {
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Throwable if unable to compute a result
     */
    V call() throws Throwable;

    /**
     * 增强call方法，捕获异常，并包装成JrlSpanBusinessException
     *
     * @return
     * @throws JrlSpanBusinessException
     */
    default V call0() throws JrlSpanBusinessException {
        try {
            return call();
        } catch (Throwable e) {
            throw new JrlSpanBusinessException(e);
        }
    }

    /**
     * 业务异常包装类
     */
    class JrlSpanBusinessException extends RuntimeException {
        private final Throwable throwable;

        public JrlSpanBusinessException(Throwable throwable) {
            super(throwable);
            this.throwable = throwable;
        }

        public Throwable getThrowable() {
            return throwable;
        }
    }
}