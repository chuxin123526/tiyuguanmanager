package cn.wheel.tiyuguanmanager.user.dao.criteria;

/**
 * 根据用户所属的角色名称查找用户对象
 * 
 * @author DENG YURONG
 * 
 */
public class UserRoleNameCriteria extends AbstractDaoCriteria {
	public UserRoleNameCriteria(String roleName) {
		super(TYPE_USER_ROLE_NAME, OP_EQUAL, roleName);
	}
}
