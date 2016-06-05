package cn.wheel.tiyuguanmanager.user.dao.criteria;

import cn.wheel.tiyuguanmanager.common.dao.criteria.AbstractDaoCriteria;

/**
 * ʹ���û�������Ϊ��ѯ����
 * 
 * @author DENG YURONG
 * 
 */
public class UserPasswordCriteria extends AbstractDaoCriteria {
	/**
	 * ����һ���û������ѯ����
	 * 
	 * @param password
	 *            ����
	 */
	public UserPasswordCriteria(String password) {
		super(TYPE_USER_PASSWORD, OP_EQUAL, password);
	}
}
