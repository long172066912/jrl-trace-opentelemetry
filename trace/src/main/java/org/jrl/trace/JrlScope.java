package org.jrl.trace;

/**
 * span作用域
 *
 * @author JerryLong
 */
public interface JrlScope {
    /**
     * 结束作用域
     */
    void close();
}
