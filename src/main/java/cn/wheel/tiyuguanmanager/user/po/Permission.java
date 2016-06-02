package cn.wheel.tiyuguanmanager.user.po;

public class Permission {
	private long id;
	private int type;

	public Permission() {

	}

	public Permission(int type) {
		this.type = type;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
