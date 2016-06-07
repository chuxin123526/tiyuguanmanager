package cn.wheel.tiyuguanmanager.announcement.dao.criteria.announcement;

import cn.wheel.tiyuguanmanager.common.dao.criteria.AbstractDaoCriteria;

public class AnnouncementMultiTypeCriteria extends AbstractDaoCriteria {
	public AnnouncementMultiTypeCriteria(int[] type) {
		super(TYPE_ANNOUNCEMENT_MULTI_STATUS, OP_EQUAL, type);
	}
}
