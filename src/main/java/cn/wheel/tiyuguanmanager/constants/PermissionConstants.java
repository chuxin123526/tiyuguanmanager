package cn.wheel.tiyuguanmanager.constants;

import java.util.ArrayList;
import java.util.List;

import cn.wheel.tiyuguanmanager.user.po.Permission;
import cn.wheel.tiyuguanmanager.user.po.User;

public class PermissionConstants {
	private static List<PermissionItem> ALL_PERMISSIONS;

	public static boolean isUserAdminPermission(int permission) {
		return (permission >= 10 && permission <= 17);
	}

	public static boolean hasUserAdminPermission(User user) {
		for (Permission permission : user.getRole().getPermissions()) {
			if (isUserAdminPermission(permission.getType())) {
				return true;
			}
		}

		return false;
	}

	public static boolean isAnnouncementPermission(int permission) {
		if (permission == 30 || permission == 32) {
			return false;
		}

		return (permission >= 31 && permission <= 39);
	}

	public static boolean hasAnnouncementPermission(User user) {
		for (Permission permission : user.getRole().getPermissions()) {
			if (isAnnouncementPermission(permission.getType())) {
				return true;
			}
		}

		return false;
	}

	public static List<PermissionItem> getAllPermissionsList() {
		if (ALL_PERMISSIONS == null) {
			ALL_PERMISSIONS = new ArrayList<>();

			// 角色相关的权限
			ALL_PERMISSIONS.add(new PermissionItem("创建角色", PERMISSION_ADMIN_ROLE_CREATE));
			ALL_PERMISSIONS.add(new PermissionItem("删除角色", PERMISSION_ADMIN_ROLE_DELETE));
			ALL_PERMISSIONS.add(new PermissionItem("变更角色", PERMISSION_ADMIN_ROLE_UPDATE));
			ALL_PERMISSIONS.add(new PermissionItem("查询角色", PERMISSION_ADMIN_ROLE_QUERY));

			// 用户相关的权限
			ALL_PERMISSIONS.add(new PermissionItem("创建用户", PERMISSION_ADMIN_USER_CREATE));
			ALL_PERMISSIONS.add(new PermissionItem("查询用户", PERMISSION_ADMIN_USER_QUERY));
			ALL_PERMISSIONS.add(new PermissionItem("停用用户", PERMISSION_ADMIN_USER_FORBID));
			ALL_PERMISSIONS.add(new PermissionItem("启用用户", PERMISSION_ADMIN_USER_ENABLE));
			ALL_PERMISSIONS.add(new PermissionItem("变更用户", PERMISSION_ADMIN_USER_UPDATE));
			ALL_PERMISSIONS.add(new PermissionItem("用户认证", PERMISSION_ADMIN_USER_VERIFY));
			ALL_PERMISSIONS.add(new PermissionItem("撤销用户认证", PERMISSION_ADMIN_USER_VERIFY_CANCEL));
			ALL_PERMISSIONS.add(new PermissionItem("查看用户详情", PERMISSION_USER_DETAIL_INFO));

			// 前台公告的权限
			ALL_PERMISSIONS.add(new PermissionItem("查看公告及评论", PERMISSION_ANNOUNCEMENT_VIEW));
			ALL_PERMISSIONS.add(new PermissionItem("发布评论", PERMISSION_ANNOUNCEMENT_COMMENT_PUBLISH));

			// 后台公告的权限
			ALL_PERMISSIONS.add(new PermissionItem("发布公告", PERMISSION_ADMIN_ANNOUNCEMENT_PUBLISH));
			ALL_PERMISSIONS.add(new PermissionItem("发布草稿", PERMISSION_ADMIN_ANNOUNCEMENT_PUBLISH_DRAFT));
			ALL_PERMISSIONS.add(new PermissionItem("删除公告", PERMISSION_ADMIN_ANNOUNCEMENT_DELETE));
			ALL_PERMISSIONS.add(new PermissionItem("恢复公告", PERMISSION_ADMIN_ANNOUNCEMENT_RECOVER));
			ALL_PERMISSIONS.add(new PermissionItem("评论查询", PERMISSION_ADMIN_ANNOUNCEMENT_COMMENT_QUERY));
			ALL_PERMISSIONS.add(new PermissionItem("隐藏公告评论", PERMISSION_ADMIN_ANNOUNCEMENT_COMMENT_HIDE));
			ALL_PERMISSIONS.add(new PermissionItem("恢复公告评论", PERMISSION_ADMIN_ANNOUNCEMENT_COMMENT_RECOVER));
		}

		return ALL_PERMISSIONS;
	}

	/**
	 * 用户后台管理：添加角色的权限
	 */
	public static final int PERMISSION_ADMIN_ROLE_CREATE = 1;

	/**
	 * 用户后台管理：删除角色的权限
	 */
	public static final int PERMISSION_ADMIN_ROLE_DELETE = 2;

	/**
	 * 用户后台管理：变更角色的权限
	 */
	public static final int PERMISSION_ADMIN_ROLE_UPDATE = 3;

	/**
	 * 用户后台管理：查询角色的权限
	 */
	public static final int PERMISSION_ADMIN_ROLE_QUERY = 4;

	/**
	 * 用户后台管理：创建用户的权限
	 */
	public static final int PERMISSION_ADMIN_USER_CREATE = 10;

	/**
	 * 用户后台管理：查询用户详细信息的权限
	 */
	public static final int PERMISSION_ADMIN_USER_QUERY = 11;

	/**
	 * 用户后台管理：停用用户的权限
	 */
	public static final int PERMISSION_ADMIN_USER_FORBID = 12;

	/**
	 * 用户后台管理：启用用户的权限
	 */
	public static final int PERMISSION_ADMIN_USER_ENABLE = 13;

	/**
	 * 用户后台管理：变更用户信息的权限
	 */
	public static final int PERMISSION_ADMIN_USER_UPDATE = 14;

	/**
	 * 用户后台管理：对用户信息进行认证的权限
	 */
	public static final int PERMISSION_ADMIN_USER_VERIFY = 15;

	/**
	 * 用户后台管理：对用户信息认证撤销的权限
	 */
	public static final int PERMISSION_ADMIN_USER_VERIFY_CANCEL = 16;

	/**
	 * 用户管理：查看用户详情的权限
	 */
	public static final int PERMISSION_USER_DETAIL_INFO = 17;

	/**
	 * 前台公告权限：查看公告及评论
	 */
	public static final int PERMISSION_ANNOUNCEMENT_VIEW = 30;

	/**
	 * 公告后台管理：发布公告
	 */
	public static final int PERMISSION_ADMIN_ANNOUNCEMENT_PUBLISH = 31;

	/**
	 * 前台公告权限：发布公告评论
	 */
	public static final int PERMISSION_ANNOUNCEMENT_COMMENT_PUBLISH = 32;

	/**
	 * 公告后台管理：发布草稿
	 */
	public static final int PERMISSION_ADMIN_ANNOUNCEMENT_PUBLISH_DRAFT = 33;

	/**
	 * 公告后台管理：评论查询
	 */
	public static final int PERMISSION_ADMIN_ANNOUNCEMENT_COMMENT_QUERY = 34;

	/**
	 * 公告后台管理：公告修改
	 */
	public static final int PERMISSION_ADMIN_ANNOUNCEMENT_UPDATE = 35;

	/**
	 * 公告后台管理：删除公告
	 */
	public static final int PERMISSION_ADMIN_ANNOUNCEMENT_DELETE = 36;

	/**
	 * 公告后台管理：恢复公告
	 */
	public static final int PERMISSION_ADMIN_ANNOUNCEMENT_RECOVER = 37;

	/**
	 * 公告后台管理：隐藏公告评论
	 */
	public static final int PERMISSION_ADMIN_ANNOUNCEMENT_COMMENT_HIDE = 38;

	/**
	 * 公告后台管理：重新显示公告评论
	 */
	public static final int PERMISSION_ADMIN_ANNOUNCEMENT_COMMENT_RECOVER = 39;
}
