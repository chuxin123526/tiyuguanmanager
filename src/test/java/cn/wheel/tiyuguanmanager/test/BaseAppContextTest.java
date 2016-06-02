package cn.wheel.tiyuguanmanager.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseAppContextTest {

	private static AbstractApplicationContext appContext;

	public ApplicationContext getApplicationContext() {
		return appContext;
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		appContext = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		appContext.close();
	}

}
