package cn.wheel.tiyuguanmanager.user.dao.criteria;

import cn.wheel.tiyuguanmanager.common.dao.criteria.AbstractDaoCriteria;

/**
 * 使用用户的账户类型作为查询条件
 * 
 * @author DENG YURONG
 * 
 */
public class UserAccountTypeCriteria extends AbstractDaoCriteria {

	/**
	 * 使用用户的账户类型作为查询条件
	 * 
	 * @param student
	 *            是否包括学生账号
	 * @param teacher
	 *            是否包括老师账号
	 * @param employee
	 *            是否包括工作人员账号
	 */
	public UserAccountTypeCriteria(boolean student, boolean teacher, boolean employee) {
		super(TYPE_USER_ACCOUNT_TYPE, OP_EQUAL, null);
		
		int criteria = 0;
		
		if (student) {
			criteria = criteria | 0x1;
		}
		
		if (teacher) {
			criteria = criteria | 0x2;
		}
		
		if (employee) {
			criteria = criteria | 0x4;
		}
		
		this.setOp(criteria);
	}
}
