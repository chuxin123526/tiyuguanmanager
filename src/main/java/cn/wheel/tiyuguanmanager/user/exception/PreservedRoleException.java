package cn.wheel.tiyuguanmanager.user.exception;

/**
 * 尝试删除系统保留用户角色时抛出的异常
 * 
 * @author DENG YURONG
 * 
 */
public class PreservedRoleException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PreservedRoleException(String roleName) {
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
