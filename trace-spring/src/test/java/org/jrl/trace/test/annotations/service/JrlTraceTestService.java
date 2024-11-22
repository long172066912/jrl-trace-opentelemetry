package org.jrl.trace.test.annotations.service;

import org.jrl.trace.test.annotations.model.Req;
import org.jrl.trace.test.annotations.model.Res;

/**
* @author JerryLong
* @version V1.0
*/
public interface JrlTraceTestService {
    /**
     * 测试
     * @param req
     * @return
     */
    Res test(Req req);

    /**
     * 测试1
     * @param req
     * @return
     */
    Res test1(Req req);
}
