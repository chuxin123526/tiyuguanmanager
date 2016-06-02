package cn.wheel.tiyuguanmanager.user.dao.criteria;

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
	 */
	public UserNameCriteria(String username) {
		super(TYPE_USER_USERNAME, OP_EQUAL, username);
	}
}
