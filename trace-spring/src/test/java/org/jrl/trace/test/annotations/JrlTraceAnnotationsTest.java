package org.jrl.trace.test.annotations;

import org.jrl.trace.BaseTest;
import org.jrl.trace.test.annotations.model.Req;
import org.jrl.trace.test.annotations.model.Res;
import org.jrl.trace.test.annotations.service.JrlTraceTestService;
import org.jrl.trace.utils.JrlTracerUtil;
import org.jrl.trace.utils.OtelIdGeneratorUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.annotation.Resource;

public class JrlTraceAnnotationsTest extends BaseTest {

    @Resource
    private JrlTraceTestService jrlTraceTestService;

    @Test
    public void test() {
        JrlTracerUtil.setTraceContext(OtelIdGeneratorUtils.generateTraceId(), OtelIdGeneratorUtils.generateSpanId());

        String s = "bb";
        final Res res = jrlTraceTestService.test(new Req(s, 1));
        Assertions.assertNotNull(res);
        Assertions.assertEquals(res.getName(), s);
        Assertions.assertEquals(res.getTraceId(), JrlTracerUtil.getTraceId());

        final Res res1 = jrlTraceTestService.test1(new Req(s, 2));
        Assertions.assertNotNull(res);
        Assertions.assertEquals(res.getName(), s);
        Assertions.assertEquals(res.getTraceId(), JrlTracerUtil.getTraceId());
    }
}
