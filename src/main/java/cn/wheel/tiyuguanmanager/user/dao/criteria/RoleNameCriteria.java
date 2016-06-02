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
}
