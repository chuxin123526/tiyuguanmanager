package cn.wheel.tiyuguanmanager.user.dao.criteria;

/**
 * ��ѯ�û�ʱ�Ƿ񽫱����õ��û��ų�
 * 
 * @author DENG YURONG
 * 
 */
public class UserForbiddenExcludeCriteria extends AbstractDaoCriteria {
	public UserForbiddenExcludeCriteria(boolean exclude) {
		super(TYPE_USER_EXCLUED_FORBIDDEN, (exclude ? OP_TRUE : OP_FALSE), null);
	}
}
