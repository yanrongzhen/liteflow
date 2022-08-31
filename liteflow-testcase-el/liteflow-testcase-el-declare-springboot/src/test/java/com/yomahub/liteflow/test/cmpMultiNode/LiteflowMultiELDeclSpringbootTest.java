package com.yomahub.liteflow.test.cmpMultiNode;

import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import com.yomahub.liteflow.test.BaseTest;
import com.yomahub.liteflow.test.cmpRetry.LiteflowRetryELDeclSpringbootTest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


/**
 * 测试springboot下的节点执行器
 * @author sorghum
 */
@RunWith(SpringRunner.class)
@TestPropertySource(value = "classpath:/cmpMulti/application.properties")
@SpringBootTest(classes = LiteflowMultiELDeclSpringbootTest.class)
@EnableAutoConfiguration
@ComponentScan({"com.yomahub.liteflow.test.cmpMultiNode.cmp"})
public class LiteflowMultiELDeclSpringbootTest extends BaseTest {

    @Resource
    private FlowExecutor flowExecutor;

    //全局重试配置测试
    @Test
    public void testBase() throws Exception{
        LiteflowResponse response = flowExecutor.execute2Resp("chain1", "arg");
        Assert.assertTrue(response.isSuccess());
        String executeStepStr = response.getExecuteStepStr();
        Assert.assertEquals("a==>b==>c==>d==>e",executeStepStr);
    }
}
