package com.yomahub.liteflow.test.script.groovy.throwException;

import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import com.yomahub.liteflow.slot.DefaultContext;
import com.yomahub.liteflow.test.BaseTest;
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
 * 测试springboot下的groovy脚本抛错
 *
 * @author Bryan.Zhang
 * @since 2.9.4
 */
@RunWith(SpringRunner.class)
@TestPropertySource(value = "classpath:/throwException/application.properties")
@SpringBootTest(classes = ThrowExceptionScriptGroovyELTest.class)
@EnableAutoConfiguration
@ComponentScan({ "com.yomahub.liteflow.test.script.groovy.throwException.cmp" })
public class ThrowExceptionScriptGroovyELTest extends BaseTest {

	@Resource
	private FlowExecutor flowExecutor;

	@Test
	public void test1() {
		LiteflowResponse response = flowExecutor.execute2Resp("chain1", "arg");
		Assert.assertFalse(response.isSuccess());
		Assert.assertEquals("T01", response.getCode());
	}

}
