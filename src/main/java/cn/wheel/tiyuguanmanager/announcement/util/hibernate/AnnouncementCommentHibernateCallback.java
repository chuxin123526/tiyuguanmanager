package cn.wheel.tiyuguanmanager.announcement.util.hibernate;

import org.hibernate.Criteria;

import cn.wheel.tiyuguanmanager.announcement.po.AnnouncementComment;
import cn.wheel.tiyuguanmanager.announcement.util.hibernate.criteria.AnnouncementCommentCriteriaUtils;
import cn.wheel.tiyuguanmanager.common.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.common.util.hibernate.PaginationHibernateCriteriaCallback;

public class AnnouncementCommentHibernateCallback extends PaginationHibernateCriteriaCallback<AnnouncementComment> {

	private static AnnouncementCommentCriteriaUtils announcementCommentCriteriaUtils;

	static {
		announcementCommentCriteriaUtils = new AnnouncementCommentCriteriaUtils();
	}

	private DaoCriteria[] criterias;

	public AnnouncementCommentHibernateCallback(DaoCriteria[] criterias) {
		super(AnnouncementComment.class);

		this.criterias = criterias;
	}

	public AnnouncementCommentHibernateCallback setCriterias(DaoCriteria[] criterias) {
		this.criterias = criterias;
		return this;
	}

	@Override
	public void doProcessCriteria(Criteria criteria) {
		announcementCommentCriteriaUtils.mergeCriteria(criteria, criterias);
	}

}
