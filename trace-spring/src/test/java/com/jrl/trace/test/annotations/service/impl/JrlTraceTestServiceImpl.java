package com.jrl.trace.test.annotations.service.impl;

import com.jrl.trace.annotations.JrlTrace;
import com.jrl.trace.test.annotations.model.Req;
import com.jrl.trace.test.annotations.model.Res;
import com.jrl.trace.test.annotations.service.JrlTraceTestService;
import com.jrl.trace.utils.JrlTracerUtil;
import org.springframework.stereotype.Service;

/**
 * @author JerryLong
 * @version V1.0
 */
@Service
public class JrlTraceTestServiceImpl implements JrlTraceTestService {
    @Override
    @JrlTrace
    public Res test(Req req) {
        return new Res(req.getName(), JrlTracerUtil.getTraceId());
    }


    @Override
    @JrlTrace(value = "test1", tags = {"a", "aa", "b", "#req.name", "c", "#req.age"})
    public Res test1(Req req) {
        return new Res(req.getName(), JrlTracerUtil.getTraceId());
    }
}
