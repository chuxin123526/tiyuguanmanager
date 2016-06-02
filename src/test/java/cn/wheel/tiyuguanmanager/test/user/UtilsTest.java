package cn.wheel.tiyuguanmanager.test.user;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import cn.wheel.tiyuguanmanager.user.util.InfoCheckUtils;

public class UtilsTest {

	@Test
	public void testCitizenIDCheck() {
		assertTrue(InfoCheckUtils.checkCitizenId("440101192001212252"));
	}

}
