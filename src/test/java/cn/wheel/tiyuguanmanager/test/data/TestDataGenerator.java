package cn.wheel.tiyuguanmanager.test.data;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;

import cn.wheel.tiyuguanmanager.constants.PermissionConstants;
import cn.wheel.tiyuguanmanager.test.BaseAppContextTest;
import cn.wheel.tiyuguanmanager.user.constants.Constants;
import cn.wheel.tiyuguanmanager.user.exception.FormException;
import cn.wheel.tiyuguanmanager.user.exception.RoleNotFoundException;
import cn.wheel.tiyuguanmanager.user.exception.UserExistException;
import cn.wheel.tiyuguanmanager.user.po.Role;
import cn.wheel.tiyuguanmanager.user.po.User;
import cn.wheel.tiyuguanmanager.user.service.role.IRoleService;
import cn.wheel.tiyuguanmanager.user.service.user.IUserService;
import cn.wheel.tiyuguanmanager.user.vo.RoleVO;
import cn.wheel.tiyuguanmanager.user.vo.UserVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDataGenerator extends BaseAppContextTest {

	private ApplicationContext context;

	private IUserService userService;
	private IRoleService roleService;

	@Before
	public void getBeans() {
		if (context == null) {
			context = getApplicationContext();
		}

		if (userService == null) {
			userService = context.getBean(IUserService.class);
		}

		if (roleService == null) {
			roleService = context.getBean(IRoleService.class);
		}
	}

	@Test
	public void data00_insertRole() {
		RoleVO registerRoleVO = new RoleVO();
		registerRoleVO.setName("注册用户");
		registerRoleVO.addPermission(PermissionConstants.PERMISSION_LOGIN);

		RoleVO verifiedRoleVO = new RoleVO();
		verifiedRoleVO.setName("认证用户");
		verifiedRoleVO.addPermission(PermissionConstants.PERMISSION_LOGIN);

		RoleVO competitionAdminRoleVO = new RoleVO();
		competitionAdminRoleVO.setName("赛事管理员");
		competitionAdminRoleVO.addPermission(PermissionConstants.PERMISSION_LOGIN);

		RoleVO superAdminRoleVO = new RoleVO();
		superAdminRoleVO.setName("超级管理员");
		superAdminRoleVO.addPermission(PermissionConstants.PERMISSION_LOGIN);

		// 用户管理相应权限
		superAdminRoleVO.addPermission(PermissionConstants.PERMISSION_USER_INSERT);
		superAdminRoleVO.addPermission(PermissionConstants.PERMISSION_USER_DISABLE);
		superAdminRoleVO.addPermission(PermissionConstants.PERMISSION_USER_QUERY);
		superAdminRoleVO.addPermission(PermissionConstants.PERMISSION_USER_UPDATE);
		superAdminRoleVO.addPermission(PermissionConstants.PERMISSION_USER_VERIFY);
		superAdminRoleVO.addPermission(PermissionConstants.PERMISSION_USER_ENABLE);

		// 角色管理相应权限
		superAdminRoleVO.addPermission(PermissionConstants.PERMISSION_ROLE_INSERT);
		superAdminRoleVO.addPermission(PermissionConstants.PERMISSION_ROLE_SELECT);
		superAdminRoleVO.addPermission(PermissionConstants.PERMISSION_ROLE_UPDATE);
		superAdminRoleVO.addPermission(PermissionConstants.PERMISSION_ROLE_DELETE);

		RoleVO playgroundAdminRoleVO = new RoleVO();
		playgroundAdminRoleVO.setName("场地管理员");
		playgroundAdminRoleVO.addPermission(PermissionConstants.PERMISSION_LOGIN);

		RoleVO instrumentAdminRoleVO = new RoleVO();
		instrumentAdminRoleVO.setName("器材管理员");
		instrumentAdminRoleVO.addPermission(PermissionConstants.PERMISSION_LOGIN);

		RoleVO systemAdminRoleVO = new RoleVO();
		systemAdminRoleVO.setName("系统管理员");
		systemAdminRoleVO.addPermission(PermissionConstants.PERMISSION_LOGIN);

		RoleVO announcementAdminRoleVO = new RoleVO();
		announcementAdminRoleVO.setName("公告管理员");
		announcementAdminRoleVO.addPermission(PermissionConstants.PERMISSION_LOGIN);

		roleService.insertRole(registerRoleVO);
		roleService.insertRole(verifiedRoleVO);
		roleService.insertRole(competitionAdminRoleVO);
		roleService.insertRole(superAdminRoleVO);
		roleService.insertRole(playgroundAdminRoleVO);
		roleService.insertRole(instrumentAdminRoleVO);
		roleService.insertRole(systemAdminRoleVO);
	}

	@Test
	public void data01_insertUser() throws FormException, UserExistException, RoleNotFoundException {
		UserVO normalUser = new UserVO();
		normalUser.setGender(1);
		normalUser.setIdentifierNumber("440103198502132541");
		normalUser.setIdentifierType(Constants.IdentifierType.TYPE_CITIZEN_ID);
		normalUser.setPassword("123456");
		normalUser.setRealname("123456");
		normalUser.setStudentNumber("201311701407");
		normalUser.setUsername("一个超级大帅逼");
		normalUser.setMobilePhone("18320481195");
		normalUser.setAccountType(Constants.UserType.TYPE_STUDENT);

		UserVO superAdmin = new UserVO();
		superAdmin.setGender(1);
		superAdmin.setIdentifierNumber("440103198502132541");
		superAdmin.setIdentifierType(Constants.IdentifierType.TYPE_CITIZEN_ID);
		superAdmin.setPassword("123456");
		superAdmin.setRealname("123456");
		superAdmin.setStudentNumber("201311701407");
		superAdmin.setUsername("一个超级管理员");
		superAdmin.setMobilePhone("18320481195");
		superAdmin.setAccountType(Constants.UserType.TYPE_EMPLOYEE);
		
		List<Role> list = roleService.findByName("超级管理员");
		superAdmin.setRoleId((int)list.get(0).getRoleId());
	
		userService.register(normalUser);
		userService.insertUser(superAdmin);

		User user = userService.findUserByUsername("一个超级管理员", true).get(0);
		userService.updateUserRole(user, "超级管理员");
	}
}
