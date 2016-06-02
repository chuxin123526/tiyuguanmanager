package cn.wheel.tiyuguanmanager.user.vo;

import java.util.ArrayList;
import java.util.List;

public class RoleVO {
	private long id;
	private String name;
	private List<Integer> permissions;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Integer> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Integer> permissions) {
		this.permissions = permissions;
	}

	public void addPermission(int type) {
		if (this.permissions == null) {
			this.permissions = new ArrayList<>();
		}
		
		this.permissions.add(type);
	}
}
