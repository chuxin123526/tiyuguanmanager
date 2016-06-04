package cn.wheel.tiyuguanmanager.user.dao.criteria;

/**
 * 使用角色名作为查询条件
 * 
 * @author DENG YURONG
 * 
 */
public class RoleNameCriteria extends AbstractDaoCriteria {

	/**
	 * 创建一个角色名查询条件
	 * 
	 * @param roleName
	 *            角色名
	 */
	public RoleNameCriteria(String roleName) {
		super(TYPE_ROLE_NAME, OP_EQUAL, roleName);
	}

	/**
	 * 创建一个角色名查询条件
	 * 
	 * @param roleName
	 *            角色名
	 * @param accurate
	 *            是否精确匹配
	 */
	public RoleNameCriteria(String roleName, boolean accurate) {
		super(TYPE_ROLE_NAME, (accurate ? OP_EQUAL : OP_LIKE), roleName);
	}
}
