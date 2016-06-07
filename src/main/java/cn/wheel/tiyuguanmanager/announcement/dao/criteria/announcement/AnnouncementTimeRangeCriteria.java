package cn.wheel.tiyuguanmanager.announcement.dao.criteria.announcement;

import java.util.Date;

import cn.wheel.tiyuguanmanager.common.dao.criteria.AbstractDaoCriteria;

public class AnnouncementTimeRangeCriteria extends AbstractDaoCriteria {
	public AnnouncementTimeRangeCriteria(Date beginTime, Date endTime) {
		super(TYPE_ANNOUNCEMENT_PUBLISH_TIME_RANGE, OP_EQUAL, new Date[] { beginTime, endTime });
	}
}
