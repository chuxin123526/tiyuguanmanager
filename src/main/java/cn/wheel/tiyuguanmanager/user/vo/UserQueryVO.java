package cn.wheel.tiyuguanmanager.user.vo;

public class UserQueryVO {
	private int[] criteria;
	private String username;
	private long roleId;
	private int accountType[];
	private int forbidden;
	private int page;

	public int[] getCriteria() {
		return criteria;
	}

	public void setCriteria(int[] criteria) {
		this.criteria = criteria;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public int[] getAccountType() {
		return accountType;
	}

	public void setAccountType(int[] accountType) {
		this.accountType = accountType;
	}

	public int getForbidden() {
		return forbidden;
	}

	public void setForbidden(int forbidden) {
		this.forbidden = forbidden;
	}

	public int getPage() {
		if (page < 1) {
			return 1;
		}
		
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
