package cn.wheel.tiyuguanmanager.user.dao.criteria;

import cn.wheel.tiyuguanmanager.common.dao.criteria.AbstractDaoCriteria;

/**
 * ʹ���û�����Ϊ��ѯ����
 * 
 * @author DENG YURONG
 * 
 */
public class UserNameCriteria extends AbstractDaoCriteria {
	/**
	 * ����һ���û�����ѯ����
	 * 
	 * @param username
	 *            �û���
	 * @param accurate
	 *            �Ƿ�ȷ��ѯ
	 */
	public UserNameCriteria(String username, boolean accurate) {
		super(TYPE_USER_USERNAME, (accurate ? OP_EQUAL : OP_LIKE), username);
	}
}
