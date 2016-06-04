package cn.wheel.tiyuguanmanager.user.exception;

/**
 * ����ɾ��ϵͳ�����û���ɫʱ�׳����쳣
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
