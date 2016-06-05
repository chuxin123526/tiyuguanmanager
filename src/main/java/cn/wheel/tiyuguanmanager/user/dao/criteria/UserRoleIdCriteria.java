package cn.wheel.tiyuguanmanager.user.dao.criteria;

import cn.wheel.tiyuguanmanager.common.dao.criteria.AbstractDaoCriteria;

/**
 * ʹ���û������Ľ�ɫ�ı����Ϊ��ѯ����
 * 
 * @author DENG YURONG
 * 
 */
public class UserRoleIdCriteria extends AbstractDaoCriteria {

	public UserRoleIdCriteria(long roleId) {
		super(TYPE_USER_ROLE_ID, OP_EQUAL, roleId);
	}

}
