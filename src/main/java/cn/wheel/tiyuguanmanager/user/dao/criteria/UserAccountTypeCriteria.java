package cn.wheel.tiyuguanmanager.user.dao.criteria;

import cn.wheel.tiyuguanmanager.common.dao.criteria.AbstractDaoCriteria;

/**
 * ʹ���û����˻�������Ϊ��ѯ����
 * 
 * @author DENG YURONG
 * 
 */
public class UserAccountTypeCriteria extends AbstractDaoCriteria {

	/**
	 * ʹ���û����˻�������Ϊ��ѯ����
	 * 
	 * @param student
	 *            �Ƿ����ѧ���˺�
	 * @param teacher
	 *            �Ƿ������ʦ�˺�
	 * @param employee
	 *            �Ƿ����������Ա�˺�
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
