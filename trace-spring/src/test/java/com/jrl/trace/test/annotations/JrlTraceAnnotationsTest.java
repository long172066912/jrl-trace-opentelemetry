package com.jrl.trace.test.annotations;//package com.wb.cache.test.cache2.commands.assertTest;

import com.jrl.trace.BaseTest;
import com.jrl.trace.test.annotations.model.Req;
import com.jrl.trace.test.annotations.model.Res;
import com.jrl.trace.test.annotations.service.JrlTraceTestService;
import com.jrl.trace.utils.JrlTracerUtil;
import com.jrl.trace.utils.OtelIdGeneratorUtils;
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
