package cn.wheel.tiyuguanmanager.constants;

public class PermissionItem {
	private String name;
	private int type;

	public PermissionItem(String name, int type) {
		super();
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
