package cn.wheel.tiyuguanmanager.user.dao.criteria;

import cn.wheel.tiyuguanmanager.common.dao.criteria.AbstractDaoCriteria;

/**
 * 使用用户名作为查询条件
 * 
 * @author DENG YURONG
 * 
 */
public class UserNameCriteria extends AbstractDaoCriteria {
	/**
	 * 创建一个用户名查询条件
	 * 
	 * @param username
	 *            用户名
	 * @param accurate
	 *            是否精确查询
	 */
	public UserNameCriteria(String username, boolean accurate) {
		super(TYPE_USER_USERNAME, (accurate ? OP_EQUAL : OP_LIKE), username);
	}
}
