package cn.wheel.tiyuguanmanager.user.util;

import java.util.Set;

import cn.wheel.tiyuguanmanager.user.po.Permission;
import cn.wheel.tiyuguanmanager.user.po.User;

/**
 * 一些用户权限相关的工具类
 * 
 * @author DENG YURONG
 * 
 */
public class UserPermissionUtils {
	public boolean userHasPermission(User user, int permission) {
		Set<Permission> permissions = user.getRole().getPermissions();
		
		for (Permission permissionObj : permissions) {
			if (permissionObj.getType() == permission) {
				return true;
			}
		}
		
		return false;
	}
}
