package cn.wheel.tiyuguanmanager.user.exception;

public class RoleIsInUseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RoleIsInUseException(String roleName) {
		super();
		this.roleName = roleName;
	}

	private String roleName;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
