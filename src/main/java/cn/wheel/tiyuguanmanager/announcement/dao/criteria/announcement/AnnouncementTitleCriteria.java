package cn.wheel.tiyuguanmanager.announcement.dao.criteria.announcement;

import cn.wheel.tiyuguanmanager.common.dao.criteria.AbstractDaoCriteria;

public class AnnouncementTitleCriteria extends AbstractDaoCriteria {
	public AnnouncementTitleCriteria(String title, boolean accurateMatch) {
		super(TYPE_ANNOUNCEMENT_TITLE, (accurateMatch ? OP_EQUAL : OP_LIKE), title);
	}
}
