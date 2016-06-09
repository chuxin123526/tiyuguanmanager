package cn.wheel.tiyuguanmanager.announcement.dao.criteria.announcement.comment;

import cn.wheel.tiyuguanmanager.common.dao.criteria.AbstractDaoCriteria;

public class AnnouncementCommentAnnouncementTitleCriteria extends AbstractDaoCriteria {
	public AnnouncementCommentAnnouncementTitleCriteria(String content) {
		super(TYPE_ANNOUNCEMENT_COMMENT_ANNOUNCEMENT_TITLE, OP_LIKE, content);
	}
}
