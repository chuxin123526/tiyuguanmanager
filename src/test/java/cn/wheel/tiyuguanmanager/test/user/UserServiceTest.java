package cn.wheel.tiyuguanmanager.test.user;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;

import cn.wheel.tiyuguanmanager.test.BaseAppContextTest;
import cn.wheel.tiyuguanmanager.user.constants.Constants;
import cn.wheel.tiyuguanmanager.user.exception.FormException;
import cn.wheel.tiyuguanmanager.user.exception.UserExistException;
import cn.wheel.tiyuguanmanager.user.po.User;
import cn.wheel.tiyuguanmanager.user.service.user.IUserService;
import cn.wheel.tiyuguanmanager.user.vo.UserVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserServiceTest extends BaseAppContextTest {

	private ApplicationContext context;
	private IUserService userService;

	@Before
	public void init() {
		if (context == null) {
			context = getApplicationContext();
		}

		if (userService == null) {
			userService = context.getBean(IUserService.class);
		}
	}

	@Test
	public void test00_Register() throws FormException, UserExistException {
		UserVO vo = new UserVO();
		vo.setGender(0);
		vo.setIdentifierNumber("440103198502132541");
		vo.setIdentifierType(Constants.IdentifierType.TYPE_CITIZEN_ID);
		vo.setPassword("123456");
		vo.setRealname("123456");
		vo.setStudentNumber("201311701407");
		vo.setUsername("username");

		userService.register(vo);
	}

	@Test
	public void test01_LoginSuccess() {
		User user = userService.login("username", "123456");
		Assert.assertNotNull(user);
	}

	@Test
	public void test02_LoginFailed() {
		User user = userService.login("username", "1234567");
		Assert.assertNull(user);
	}
}
