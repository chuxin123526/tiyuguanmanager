package cn.wheel.tiyuguanmanager.announcement.dao.criteria.announcement.comment;

import java.util.Date;

import cn.wheel.tiyuguanmanager.common.dao.criteria.AbstractDaoCriteria;

public class AnnouncementCommentTimeRangeCriteria extends AbstractDaoCriteria {
	public AnnouncementCommentTimeRangeCriteria(Date beginTime, Date endTime) {
		super(TYPE_ANNOUNCEMENT_COMMNET_PUBLISH_TIME_RANGE, OP_EQUAL, new Date[] { beginTime, endTime });
	}
}
