package cn.wheel.tiyuguanmanager.announcement.dao.criteria.announcement;

import cn.wheel.tiyuguanmanager.common.dao.criteria.AbstractDaoCriteria;

public class AnnouncementKeywordSearchCriteria extends AbstractDaoCriteria {
	public AnnouncementKeywordSearchCriteria(String keyword) {
		super(TYPE_ANNOUNCEMENT_COMMENT_SEARCH_KEYWORD, OP_EQUAL, keyword);
	}
}
