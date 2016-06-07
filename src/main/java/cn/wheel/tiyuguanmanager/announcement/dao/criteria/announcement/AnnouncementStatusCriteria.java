package cn.wheel.tiyuguanmanager.announcement.dao.criteria.announcement;

import cn.wheel.tiyuguanmanager.common.dao.criteria.AbstractDaoCriteria;

public class AnnouncementStatusCriteria extends AbstractDaoCriteria {
	public AnnouncementStatusCriteria(int status) {
		super(TYPE_ANNOUNCEMENT_STATUS, OP_EQUAL, status);
	}
}
