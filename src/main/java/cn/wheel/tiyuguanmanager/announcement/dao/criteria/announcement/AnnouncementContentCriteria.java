package cn.wheel.tiyuguanmanager.announcement.dao.criteria.announcement;

import cn.wheel.tiyuguanmanager.common.dao.criteria.AbstractDaoCriteria;

public class AnnouncementContentCriteria extends AbstractDaoCriteria {
	public AnnouncementContentCriteria(String content, boolean accurateMatch) {
		super(TYPE_ANNOUNCEMENT_CONTENT, (accurateMatch ? OP_EQUAL : OP_LIKE), content);
	}
}
