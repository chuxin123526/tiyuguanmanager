package cn.wheel.tiyuguanmanager.user.vo;

public class UserQueryShowback {
	private boolean nameIncluded;
	private boolean roleIncluded;
	private boolean accountTypeIncluded;

	private String username;
	private long roleId;

	private boolean typeStudentIncluded;
	private boolean typeTeacherIncluded;
	private boolean typeEmployeeIncluded;

	private boolean forbiddenIncluded;

	private int page;

	public boolean isNameIncluded() {
		return nameIncluded;
	}

	public void setNameIncluded(boolean nameIncluded) {
		this.nameIncluded = nameIncluded;
	}

	public boolean isRoleIncluded() {
		return roleIncluded;
	}

	public void setRoleIncluded(boolean roleIncluded) {
		this.roleIncluded = roleIncluded;
	}

	public boolean isAccountTypeIncluded() {
		return accountTypeIncluded;
	}

	public void setAccountTypeIncluded(boolean accountTypeIncluded) {
		this.accountTypeIncluded = accountTypeIncluded;
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

	public boolean isTypeStudentIncluded() {
		return typeStudentIncluded;
	}

	public void setTypeStudentIncluded(boolean typeStudentIncluded) {
		this.typeStudentIncluded = typeStudentIncluded;
	}

	public boolean isTypeTeacherIncluded() {
		return typeTeacherIncluded;
	}

	public void setTypeTeacherIncluded(boolean typeTeacherIncluded) {
		this.typeTeacherIncluded = typeTeacherIncluded;
	}

	public boolean isTypeEmployeeIncluded() {
		return typeEmployeeIncluded;
	}

	public void setTypeEmployeeIncluded(boolean typeEmployeeIncluded) {
		this.typeEmployeeIncluded = typeEmployeeIncluded;
	}

	public boolean isForbiddenIncluded() {
		return forbiddenIncluded;
	}

	public void setForbiddenIncluded(boolean forbiddenIncluded) {
		this.forbiddenIncluded = forbiddenIncluded;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

}
