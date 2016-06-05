package cn.wheel.tiyuguanmanager.announcement.util.hibernate;

import org.hibernate.Criteria;

import cn.wheel.tiyuguanmanager.announcement.po.Announcement;
import cn.wheel.tiyuguanmanager.announcement.util.hibernate.criteria.AnnouncementCriteriaUtils;
import cn.wheel.tiyuguanmanager.common.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.common.util.hibernate.PaginationHibernateCriteriaCallback;

public class AnnouncementHibernateCallback extends PaginationHibernateCriteriaCallback<Announcement> {

	private static AnnouncementCriteriaUtils announcementCriteriaUtils;

	static {
		announcementCriteriaUtils = new AnnouncementCriteriaUtils();
	}

	private DaoCriteria[] criterias;

	public AnnouncementHibernateCallback(DaoCriteria[] criterias) {
		super(Announcement.class);

		this.criterias = criterias;
	}

	@Override
	public void doProcessCriteria(Criteria criteria) {
		announcementCriteriaUtils.mergeCriteria(criteria, criterias);
	}

}
