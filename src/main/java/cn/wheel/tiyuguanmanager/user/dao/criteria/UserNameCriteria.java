package cn.wheel.tiyuguanmanager.user.dao.criteria;

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
	 */
	public UserNameCriteria(String username) {
		super(TYPE_USER_USERNAME, OP_EQUAL, username);
	}
}
