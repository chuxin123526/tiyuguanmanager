package cn.wheel.tiyuguanmanager.user.dao.criteria;

/**
 * 使用用户密码作为查询条件
 * 
 * @author DENG YURONG
 * 
 */
public class UserPasswordCriteria extends AbstractDaoCriteria {
	/**
	 * 创建一个用户密码查询条件
	 * 
	 * @param password
	 *            密码
	 */
	public UserPasswordCriteria(String password) {
		super(TYPE_USER_PASSWORD, OP_EQUAL, password);
	}
}
