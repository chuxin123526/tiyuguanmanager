package cn.wheel.tiyuguanmanager.test.user;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import cn.wheel.tiyuguanmanager.test.BaseAppContextTest;
import cn.wheel.tiyuguanmanager.user.exception.RoleIsInUseException;
import cn.wheel.tiyuguanmanager.user.exception.RoleNotFoundException;
import cn.wheel.tiyuguanmanager.user.po.Role;
import cn.wheel.tiyuguanmanager.user.service.role.IRoleService;
import cn.wheel.tiyuguanmanager.user.vo.RoleVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoleServiceTest extends BaseAppContextTest {

	private IRoleService roleService;

	@Before
	public void getService() {
		if (roleService == null) {
			roleService = getApplicationContext().getBean(IRoleService.class);
		}
	}

	@Test
	public void test00_Insert() {
		RoleVO vo = new RoleVO();

		vo.setName("anotherRole");
		vo.setPermissions(new ArrayList<Integer>());

		for (int i = 0; i < 5; i++) {
			vo.getPermissions().add(i);
		}

		roleService.insertRole(vo);
	}

	@Test
	public void test01_UpdateBoth() throws RoleNotFoundException {
		RoleVO vo = new RoleVO();

		vo.setId(1);
		vo.setName("newRole");
		vo.setPermissions(new ArrayList<Integer>());

		for (int i = 0; i < 10; i++) {
			vo.getPermissions().add(i);
		}

		roleService.updateRole(vo);
	}

	@Test
	public void test02_UpdateNotExist() {
		RoleVO vo = new RoleVO();

		vo.setId(30);
		vo.setName("newRole");
		vo.setPermissions(new ArrayList<Integer>());

		for (int i = 0; i < 10; i++) {
			vo.getPermissions().add(i);
		}

		try {
			roleService.updateRole(vo);
			Assert.fail();
		} catch (RoleNotFoundException e) {

		}
	}

	@Test
	public void test03_UpdateName() throws RoleNotFoundException {
		RoleVO vo = new RoleVO();

		vo.setId(1);
		vo.setName("newName");

		roleService.updateRole(vo);
	}

	@Test
	public void test04_UpdatePermission() throws RoleNotFoundException {
		RoleVO vo = new RoleVO();

		vo.setId(1);
		vo.setPermissions(new ArrayList<Integer>());

		for (int i = 0; i < 30; i++) {
			vo.getPermissions().add(i);
		}

		roleService.updateRole(vo);
	}

	@Test
	public void test05_ListAll() {
		List<Role> list = roleService.list();
		Assert.assertEquals(2, list.size());
	}

	@Test
	public void test06_ListPart() {
		List<Role> list = roleService.list(0, 1);
		Assert.assertEquals(1, list.size());
	}

	@Test
	public void test07_ListOutOfRange() {
		List<Role> list = roleService.list(10, 1);
		Assert.assertEquals(0, list.size());
	}

	@Test
	public void test08_DeleteExist() {
		RoleVO vo = new RoleVO();
		vo.setId(1);

		try {
			roleService.deleteRole(vo);
		} catch (RoleIsInUseException e) {

		} catch (RoleNotFoundException e) {

		}
	}

	@Test
	public void test09_DeleteNotExist() {
		RoleVO vo = new RoleVO();
		vo.setId(1);

		try {
			roleService.deleteRole(vo);
			Assert.fail();
		} catch (RoleIsInUseException e) {
			Assert.fail();
		} catch (RoleNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test10_findByName() {
		List<Role> list = roleService.findByName("×¢²áÓÃ»§");
		Assert.assertEquals(1, list.size());
	}

	@Test
	public void test11_findByNameNotExist() {
		List<Role> list = roleService.findByName("×¢²á");
		Assert.assertEquals(0, list.size());
	}
}
