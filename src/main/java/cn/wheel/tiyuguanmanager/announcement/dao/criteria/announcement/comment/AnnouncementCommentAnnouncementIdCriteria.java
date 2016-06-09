package cn.wheel.tiyuguanmanager.announcement.dao.criteria.announcement.comment;

import cn.wheel.tiyuguanmanager.common.dao.criteria.AbstractDaoCriteria;

public class AnnouncementCommentAnnouncementIdCriteria extends AbstractDaoCriteria {
	public AnnouncementCommentAnnouncementIdCriteria(long announcementId) {
		super(TYPE_ANNOUNCEMENT_COMMENT_ANNOUNCEMENT_ID, OP_EQUAL, announcementId);
	}
}
