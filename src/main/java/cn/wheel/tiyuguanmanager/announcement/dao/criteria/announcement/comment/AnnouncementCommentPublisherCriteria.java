package cn.wheel.tiyuguanmanager.announcement.dao.criteria.announcement.comment;

import cn.wheel.tiyuguanmanager.common.dao.criteria.AbstractDaoCriteria;

public class AnnouncementCommentPublisherCriteria extends AbstractDaoCriteria {
	public AnnouncementCommentPublisherCriteria(long publisherId) {
		super(TYPE_ANNOUNCEMENT_COMMENT_PUBLISHER_ID, OP_EQUAL, publisherId);
	}
}
