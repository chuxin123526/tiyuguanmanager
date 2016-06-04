package cn.wheel.tiyuguanmanager.user.dao.criteria;

/**
 * ʹ�ý�ɫ����Ϊ��ѯ����
 * 
 * @author DENG YURONG
 * 
 */
public class RoleNameCriteria extends AbstractDaoCriteria {

	/**
	 * ����һ����ɫ����ѯ����
	 * 
	 * @param roleName
	 *            ��ɫ��
	 */
	public RoleNameCriteria(String roleName) {
		super(TYPE_ROLE_NAME, OP_EQUAL, roleName);
	}

	/**
	 * ����һ����ɫ����ѯ����
	 * 
	 * @param roleName
	 *            ��ɫ��
	 * @param accurate
	 *            �Ƿ�ȷƥ��
	 */
	public RoleNameCriteria(String roleName, boolean accurate) {
		super(TYPE_ROLE_NAME, (accurate ? OP_EQUAL : OP_LIKE), roleName);
	}
}
