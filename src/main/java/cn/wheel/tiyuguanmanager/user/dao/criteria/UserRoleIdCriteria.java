package cn.wheel.tiyuguanmanager.user.dao.criteria;

import cn.wheel.tiyuguanmanager.common.dao.criteria.AbstractDaoCriteria;

/**
 * 使用用户所属的角色的编号作为查询条件
 * 
 * @author DENG YURONG
 * 
 */
public class UserRoleIdCriteria extends AbstractDaoCriteria {

	public UserRoleIdCriteria(long roleId) {
		super(TYPE_USER_ROLE_ID, OP_EQUAL, roleId);
	}

}
