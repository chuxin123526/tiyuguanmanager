package cn.wheel.tiyuguanmanager.user.dao.criteria;

import cn.wheel.tiyuguanmanager.common.dao.criteria.AbstractDaoCriteria;

/**
 * 查询用户时是否将被禁用的用户排除
 * 
 * @author DENG YURONG
 * 
 */
public class UserForbiddenExcludeCriteria extends AbstractDaoCriteria {
	public UserForbiddenExcludeCriteria(boolean exclude) {
		super(TYPE_USER_EXCLUED_FORBIDDEN, (exclude ? OP_TRUE : OP_FALSE), null);
	}
}
