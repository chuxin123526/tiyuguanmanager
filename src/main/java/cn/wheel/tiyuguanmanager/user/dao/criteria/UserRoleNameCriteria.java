package cn.wheel.tiyuguanmanager.user.dao.criteria;

import cn.wheel.tiyuguanmanager.common.dao.criteria.AbstractDaoCriteria;

/**
 * �����û������Ľ�ɫ���Ʋ����û�����
 * 
 * @author DENG YURONG
 * 
 */
public class UserRoleNameCriteria extends AbstractDaoCriteria {
	public UserRoleNameCriteria(String roleName) {
		super(TYPE_USER_ROLE_NAME, OP_EQUAL, roleName);
	}
}
