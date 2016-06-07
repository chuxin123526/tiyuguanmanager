package cn.wheel.tiyuguanmanager.announcement.dao.criteria.announcement;

import cn.wheel.tiyuguanmanager.common.dao.criteria.AbstractDaoCriteria;

public class AnnouncementPublisherIdCriteria extends AbstractDaoCriteria {
	public AnnouncementPublisherIdCriteria(long publisherId) {
		super(TYPE_ANNOUNCEMENT_PUBLISHER_ID, OP_EQUAL, publisherId);
	}
}
