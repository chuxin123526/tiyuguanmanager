package cn.wheel.tiyuguanmanager.announcement.dao.criteria.announcement.comment;

import cn.wheel.tiyuguanmanager.common.dao.criteria.AbstractDaoCriteria;

public class AnnouncementCommentMultyTypeCriteria extends AbstractDaoCriteria {
	public AnnouncementCommentMultyTypeCriteria(int[] types) {
		super(TYPE_ANNOUNCEMENT_COMMENT_MULTI_TYPE, OP_EQUAL, types);
	}
}
