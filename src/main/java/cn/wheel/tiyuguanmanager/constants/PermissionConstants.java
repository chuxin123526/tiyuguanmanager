package cn.wheel.tiyuguanmanager.constants;

import java.util.ArrayList;
import java.util.List;

public class PermissionConstants {
	private static List<PermissionItem> ALL_PERMISSIONS;

	public static List<PermissionItem> getAllPermissionsList() {
		if (ALL_PERMISSIONS == null) {
			ALL_PERMISSIONS = new ArrayList<>();

			ALL_PERMISSIONS.add(new PermissionItem("登录", PERMISSION_LOGIN));

			// 角色相关权限
			ALL_PERMISSIONS.add(new PermissionItem("添加角色", PERMISSION_ROLE_INSERT));
			ALL_PERMISSIONS.add(new PermissionItem("删除角色", PERMISSION_ROLE_DELETE));
			ALL_PERMISSIONS.add(new PermissionItem("更改角色", PERMISSION_ROLE_UPDATE));
			ALL_PERMISSIONS.add(new PermissionItem("角色查询", PERMISSION_ROLE_SELECT));

			// 用户相关权限
			ALL_PERMISSIONS.add(new PermissionItem("添加用户", PERMISSION_USER_INSERT));
			ALL_PERMISSIONS.add(new PermissionItem("停用用户", PERMISSION_USER_DISABLE));
			ALL_PERMISSIONS.add(new PermissionItem("启用用户", PERMISSION_USER_ENABLE));
			ALL_PERMISSIONS.add(new PermissionItem("用户信息变更", PERMISSION_USER_UPDATE));
			ALL_PERMISSIONS.add(new PermissionItem("用户信息查询", PERMISSION_USER_QUERY));
			ALL_PERMISSIONS.add(new PermissionItem("用户信息认证", PERMISSION_USER_VERIFY));
		}

		return ALL_PERMISSIONS;
	}

	/**
	 * 登录系统的权限
	 */
	public static final int PERMISSION_LOGIN = 1;

	/**
	 * 表示添加用户的权限
	 */
	public static final int PERMISSION_USER_INSERT = 10;

	/**
	 * 表示变更用户信息的权限
	 */
	public static final int PERMISSION_USER_UPDATE = 11;

	/**
	 * 表示禁用用户的权限
	 */
	public static final int PERMISSION_USER_DISABLE = 12;

	/**
	 * 表示对已经禁用的账号启用的权限
	 */
	public static final int PERMISSION_USER_ENABLE = 13;

	/**
	 * 表示查询用户详细信息的权限
	 */
	public static final int PERMISSION_USER_QUERY = 14;

	/**
	 * 表示审核用户信息的权限
	 */
	public static final int PERMISSION_USER_VERIFY = 15;

	/**
	 * 表示增加角色的权限
	 */
	public static final int PERMISSION_ROLE_INSERT = 20;

	/**
	 * 表示删除角色的权限
	 */
	public static final int PERMISSION_ROLE_DELETE = 21;

	/**
	 * 表示查询角色详细信息的权限
	 */
	public static final int PERMISSION_ROLE_SELECT = 22;

	/**
	 * 表示对角色信息和权限进行变更的权限
	 */
	public static final int PERMISSION_ROLE_UPDATE = 23;
}
