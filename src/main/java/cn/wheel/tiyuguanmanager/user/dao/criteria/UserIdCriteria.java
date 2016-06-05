package cn.wheel.tiyuguanmanager.user.dao.criteria;

import cn.wheel.tiyuguanmanager.common.dao.criteria.AbstractDaoCriteria;
import cn.wheel.tiyuguanmanager.common.dao.criteria.DaoCriteria;

public class UserIdCriteria extends AbstractDaoCriteria {
	public UserIdCriteria(boolean equal, long id) {
		super(DaoCriteria.TYPE_USER_USER_ID, (equal ? OP_EQUAL : OP_DISEQUAL), id);
	}
}
