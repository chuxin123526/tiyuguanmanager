package cn.wheel.tiyuguanmanager.test.data;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;

import cn.wheel.tiyuguanmanager.announcement.service.announcement.IAnnouncementService;
import cn.wheel.tiyuguanmanager.announcement.vo.AnnouncementVO;
import cn.wheel.tiyuguanmanager.common.exception.FormException;
import cn.wheel.tiyuguanmanager.constants.PermissionConstants;
import cn.wheel.tiyuguanmanager.test.BaseAppContextTest;
import cn.wheel.tiyuguanmanager.user.constants.UserConstants;
import cn.wheel.tiyuguanmanager.user.exception.RoleNotFoundException;
import cn.wheel.tiyuguanmanager.user.exception.UserExistException;
import cn.wheel.tiyuguanmanager.user.po.Role;
import cn.wheel.tiyuguanmanager.user.service.role.IRoleService;
import cn.wheel.tiyuguanmanager.user.service.user.IUserService;
import cn.wheel.tiyuguanmanager.user.vo.RoleVO;
import cn.wheel.tiyuguanmanager.user.vo.UserVO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDataGenerator extends BaseAppContextTest {

	private ApplicationContext context;

	private IUserService userService;
	private IRoleService roleService;
	private IAnnouncementService announcementService;

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

		if (announcementService == null) {
			announcementService = context.getBean(IAnnouncementService.class);
		}
	}

	@Test
	public void data00_insertRole() {
		RoleVO registerRoleVO = new RoleVO();
		registerRoleVO.setName("注册用户");
		registerRoleVO.addPermission(PermissionConstants.PERMISSION_LOGIN);
		registerRoleVO.addPermission(PermissionConstants.PERMISSION_ANNOUNCEMENT_LIST);
		registerRoleVO.addPermission(PermissionConstants.PERMISSION_ANNOUNCEMENT_VIEW);

		RoleVO verifiedRoleVO = new RoleVO();
		verifiedRoleVO.setName("认证用户");
		verifiedRoleVO.addPermission(PermissionConstants.PERMISSION_LOGIN);
		verifiedRoleVO.addPermission(PermissionConstants.PERMISSION_ANNOUNCEMENT_LIST);
		verifiedRoleVO.addPermission(PermissionConstants.PERMISSION_ANNOUNCEMENT_VIEW);
		verifiedRoleVO.addPermission(PermissionConstants.PERMISSION_ANNOUNCEMENT_COMMENT_PUBLISH);

		RoleVO competitionAdminRoleVO = new RoleVO();
		competitionAdminRoleVO.setName("赛事管理员");
		competitionAdminRoleVO.addPermission(PermissionConstants.PERMISSION_LOGIN);

		RoleVO superAdminRoleVO = new RoleVO();
		superAdminRoleVO.setName("超级管理员");
		superAdminRoleVO.addPermission(PermissionConstants.PERMISSION_LOGIN);
		superAdminRoleVO.addPermission(PermissionConstants.PERMISSION_USER_INSERT);
		superAdminRoleVO.addPermission(PermissionConstants.PERMISSION_USER_DISABLE);
		superAdminRoleVO.addPermission(PermissionConstants.PERMISSION_USER_QUERY);
		superAdminRoleVO.addPermission(PermissionConstants.PERMISSION_USER_UPDATE);
		superAdminRoleVO.addPermission(PermissionConstants.PERMISSION_USER_VERIFY);
		superAdminRoleVO.addPermission(PermissionConstants.PERMISSION_USER_ENABLE);
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
		announcementAdminRoleVO.addPermission(PermissionConstants.PERMISSION_ANNOUNCEMENT_PUBLISH);
		announcementAdminRoleVO.addPermission(PermissionConstants.PERMISSION_ANNOUNCEMENT_DEL_ANOUNCEMENT);
		announcementAdminRoleVO.addPermission(PermissionConstants.PERMISSION_ANNOUNCEMENT_LIST);
		announcementAdminRoleVO.addPermission(PermissionConstants.PERMISSION_ANNOUNCEMENT_UPDATE_ANNOUNCEMENT);
		announcementAdminRoleVO.addPermission(PermissionConstants.PERMISSION_ANNOUNCEMENT_VIEW);
		announcementAdminRoleVO.addPermission(PermissionConstants.PERMISSION_ANNOUNCEMENT_COMMENT_HIDE);
		announcementAdminRoleVO.addPermission(PermissionConstants.PERMISSION_ANNOUNCEMENT_COMMENT_PUBLISH);
		announcementAdminRoleVO.addPermission(PermissionConstants.PERMISSION_ANNOUNCEMENT_COMMENT_RECOVER);
		announcementAdminRoleVO.addPermission(PermissionConstants.PERMISSION_ANNOUNCEMENT_MANAGE_COMMENT_LIST);
		announcementAdminRoleVO.addPermission(PermissionConstants.PERMISSION_ANNOUNCEMENT_MANAGE_QUERY);

		roleService.insertRole(registerRoleVO);
		roleService.insertRole(verifiedRoleVO);
		roleService.insertRole(competitionAdminRoleVO);
		roleService.insertRole(superAdminRoleVO);
		roleService.insertRole(playgroundAdminRoleVO);
		roleService.insertRole(instrumentAdminRoleVO);
		roleService.insertRole(systemAdminRoleVO);
		roleService.insertRole(announcementAdminRoleVO);
	}

	@Test
	public void data01_insertUser() throws FormException, UserExistException, RoleNotFoundException {
		UserVO normalUser = new UserVO();
		normalUser.setGender(1);
		normalUser.setIdentifierNumber("440103198502132541");
		normalUser.setIdentifierType(UserConstants.IdentifierType.TYPE_CITIZEN_ID);
		normalUser.setPassword("123456");
		normalUser.setRealname("123456");
		normalUser.setStudentNumber("201311701407");
		normalUser.setUsername("一个超级大帅逼");
		normalUser.setMobilePhone("18320481195");
		normalUser.setAccountType(UserConstants.UserType.TYPE_STUDENT);

		UserVO superAdmin = new UserVO();
		superAdmin.setGender(1);
		superAdmin.setIdentifierNumber("440103198502132541");
		superAdmin.setIdentifierType(UserConstants.IdentifierType.TYPE_CITIZEN_ID);
		superAdmin.setPassword("123456");
		superAdmin.setRealname("123456");
		superAdmin.setStudentNumber("201311701407");
		superAdmin.setUsername("一个超级管理员");
		superAdmin.setMobilePhone("18320481195");
		superAdmin.setAccountType(UserConstants.UserType.TYPE_EMPLOYEE);

		List<Role> list = roleService.findByName("超级管理员");
		superAdmin.setRoleId((int) list.get(0).getRoleId());

		list = roleService.findByName("公告管理员");

		UserVO announceAdmin = new UserVO();
		announceAdmin.setGender(1);
		announceAdmin.setIdentifierNumber("440103198502132541");
		announceAdmin.setIdentifierType(UserConstants.IdentifierType.TYPE_CITIZEN_ID);
		announceAdmin.setPassword("123456");
		announceAdmin.setRealname("123456");
		announceAdmin.setStudentNumber("201311701407");
		announceAdmin.setUsername("一个公告管理员");
		announceAdmin.setMobilePhone("18320481195");
		announceAdmin.setAccountType(UserConstants.UserType.TYPE_EMPLOYEE);

		announceAdmin.setRoleId((int) list.get(0).getRoleId());

		userService.register(normalUser);
		userService.insertUser(superAdmin);
		userService.insertUser(announceAdmin);
	}

	@Test
	public void data02_announcement() {
		AnnouncementVO announcementVO = new AnnouncementVO();
		announcementVO.setUserId(1);
		announcementVO.setTitle("PY 交易");
		announcementVO.setContent("这背后肯定有不可告人的 PY 交易");

		announcementService.publishNewAnnouncement(announcementVO);

		announcementVO = new AnnouncementVO();
		announcementVO.setUserId(1);
		announcementVO.setTitle("我是一条正式公告");
		announcementVO.setContent("这背后肯定有不可告人的 PY 交易");

		announcementService.publishNewAnnouncement(announcementVO);

		announcementVO = new AnnouncementVO();
		announcementVO.setUserId(1);
		announcementVO.setTitle("我又是一条正式公告");
		announcementVO.setContent("这背后肯定有不可告人的 PY 交易");

		announcementService.publishNewAnnouncement(announcementVO);

		announcementVO = new AnnouncementVO();
		announcementVO.setUserId(1);
		announcementVO.setTitle("加测试数据好烦 →_→");
		announcementVO.setContent("这背后肯定有不可告人的 PY 交易");

		announcementService.publishNewAnnouncement(announcementVO);

		announcementVO = new AnnouncementVO();
		announcementVO.setUserId(1);
		announcementVO.setTitle("加测试数据好len烦 →_→");
		announcementVO.setContent("这背后肯定有不可告人的 PY 交易");

		announcementService.publishNewAnnouncement(announcementVO);

		announcementVO = new AnnouncementVO();
		announcementVO.setUserId(1);
		announcementVO.setTitle("猴赛雷");
		announcementVO.setContent("这背后肯定有不可告人的 PY 交易");

		announcementService.publishNewAnnouncement(announcementVO);

		announcementVO = new AnnouncementVO();
		announcementVO.setUserId(1);
		announcementVO.setTitle("雷猴");
		announcementVO.setContent("这背后肯定有不可告人的 PY 交易");

		announcementService.publishNewAnnouncement(announcementVO);

		announcementVO = new AnnouncementVO();
		announcementVO.setUserId(1);
		announcementVO.setTitle("USB 交易");
		announcementVO.setContent("这背后肯定有不可告人的 USB 交易（我是一条草稿）");

		announcementService.saveNewAnnouncementDraft(announcementVO);
	}
}
