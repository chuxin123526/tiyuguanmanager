package cn.wheel.tiyuguanmanager.announcement.dao.criteria.announcement;

import cn.wheel.tiyuguanmanager.common.dao.criteria.AbstractDaoCriteria;

public class AnnouncementOrderCriteria extends AbstractDaoCriteria {
	public AnnouncementOrderCriteria(boolean desc) {
		super(TYPE_ANNOUNCEMENT_TIME_ORDER, (desc ? ORDER_DESC : ORDER_ASC), null);
	}
}
