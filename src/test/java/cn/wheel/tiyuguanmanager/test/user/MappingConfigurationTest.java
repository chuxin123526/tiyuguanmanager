package cn.wheel.tiyuguanmanager.test.user;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import cn.wheel.tiyuguanmanager.test.BaseAppContextTest;

public class MappingConfigurationTest extends BaseAppContextTest {

	@Test
	public void test() {
		ApplicationContext context = getApplicationContext();
		context.getBean("dataSource", DataSource.class);
	}

}
