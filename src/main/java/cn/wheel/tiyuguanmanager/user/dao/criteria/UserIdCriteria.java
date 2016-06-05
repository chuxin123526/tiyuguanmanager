package cn.wheel.tiyuguanmanager.user.dao.criteria;

public class UserIdCriteria extends AbstractDaoCriteria {
	public UserIdCriteria(boolean equal, long id) {
		super(DaoCriteria.TYPE_USER_USER_ID, (equal ? OP_EQUAL : OP_DISEQUAL), id);
	}
}
