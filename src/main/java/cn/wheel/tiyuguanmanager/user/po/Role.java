package cn.wheel.tiyuguanmanager.user.po;

import java.util.HashSet;
import java.util.Set;

public class Role {
	private long roleId;
	private String name;
	private Set<Permission> permissions;

	public Role() {
		this.permissions = new HashSet<>();
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long id) {
		this.roleId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

	public void addPermission(Permission permission) {
		this.permissions.add(permission);
	}
	
	public boolean hasPermission(int type) {
		for (Permission permission : this.permissions) {
			if (permission.getType() == type) {
				return true;
			}
		}
		
		return false;
	}
}
