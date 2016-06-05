package cn.wheel.tiyuguanmanager.test.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.wheel.tiyuguanmanager.common.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.test.BaseAppContextTest;
import cn.wheel.tiyuguanmanager.user.constants.Constants;
import cn.wheel.tiyuguanmanager.user.dao.criteria.UserNameCriteria;
import cn.wheel.tiyuguanmanager.user.dao.criteria.UserPasswordCriteria;
import cn.wheel.tiyuguanmanager.user.dao.criteria.UserRoleNameCriteria;
import cn.wheel.tiyuguanmanager.user.dao.user.IUserDao;
import cn.wheel.tiyuguanmanager.user.po.User;

public class UserDaoTest extends BaseAppContextTest {

	private IUserDao userDao;

	@Before
	public void get() {
		if (userDao == null) {
			userDao = getApplicationContext().getBean(IUserDao.class);
		}
	}

	@Test
	public void testInsert() {
		User user = new User();
		user.setUsername("test");
		user.setPassword("password");
		user.setIdentifierType(Constants.IdentifierType.TYPE_CITIZEN_ID);
		user.setIdentifierNumber("123456789012345678");
		user.setRealname("aaa");
		user.setRole(null);
		user.setStudentNumber("123456");

		userDao.insert(user);
	}

	@Test
	public void testFindById() {
		User user = userDao.findById(1);
		assertNotNull(user);

		assertEquals("test", user.getUsername());
		assertEquals("password", user.getPassword());
	}

	@Test
	public void testUpdate() {
		User user = userDao.findById(1);
		assertNotNull(user);

		user.setUsername("fuck");
		userDao.update(user);
	}

	@Test
	public void delete() {
		User user = userDao.findById(1);
		assertNotNull(user);

		userDao.delete(user);
	}

	@Test
	public void testFindByCriteria() {
		List<User> list = userDao.find(new DaoCriteria[] { new UserNameCriteria("test", true), new UserPasswordCriteria("password1") });
		for (User user : list) {
			System.out.println(user.getUsername() + ": " + user.getPassword());
		}
	}

	@Test
	public void testFindByCriteriaPage() {
		List<User> list = userDao.find(new DaoCriteria[] { new UserNameCriteria("一个超级大帅逼", true) }, 2, 1);
		for (User user : list) {
			System.out.println(user.getUsername() + ": " + user.getPassword());
		}
	}

	@Test
	public void testGetUserCount() {
		long userCount = userDao.count();
		assertEquals(1, userCount);
	}

	@Test
	public void testFindRegisteredUser() {
		List<User> list = userDao.find(new DaoCriteria[] { new UserRoleNameCriteria("认证用户") });
		for (User user : list) {
			System.out.println(user.getUsername() + ": " + user.getPassword());
		}
	}
}
