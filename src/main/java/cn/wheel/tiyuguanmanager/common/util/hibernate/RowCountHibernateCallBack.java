package cn.wheel.tiyuguanmanager.common.util.hibernate;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate4.HibernateCallback;

import cn.wheel.tiyuguanmanager.announcement.po.Announcement;
import cn.wheel.tiyuguanmanager.announcement.po.AnnouncementComment;
import cn.wheel.tiyuguanmanager.announcement.util.hibernate.criteria.AnnouncementCommentCriteriaUtils;
import cn.wheel.tiyuguanmanager.announcement.util.hibernate.criteria.AnnouncementCriteriaUtils;
import cn.wheel.tiyuguanmanager.common.dao.criteria.CriteriaProcessor;
import cn.wheel.tiyuguanmanager.common.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.user.po.Role;
import cn.wheel.tiyuguanmanager.user.po.User;
import cn.wheel.tiyuguanmanager.user.util.hibernate.criteria.RoleCriteriaUtils;
import cn.wheel.tiyuguanmanager.user.util.hibernate.criteria.UserCriteriaUtils;

public class RowCountHibernateCallBack implements HibernateCallback<Long> {

	private static Map<Class<? extends Object>, CriteriaProcessor> processors;

	static {
		processors = new HashMap<Class<? extends Object>, CriteriaProcessor>();
		processors.put(User.class, new UserCriteriaUtils());
		processors.put(Role.class, new RoleCriteriaUtils());
		processors.put(AnnouncementComment.class, new AnnouncementCommentCriteriaUtils());
		processors.put(Announcement.class, new AnnouncementCriteriaUtils());
	}

	private DaoCriteria[] criterias;
	private Class<? extends Object> clazz;

	public RowCountHibernateCallBack(DaoCriteria[] criterias, Class<? extends Object> clazz) {
		super();
		this.criterias = criterias;
		this.clazz = clazz;
	}

	@Override
	public Long doInHibernate(Session session) throws HibernateException {
		Criteria criteria = session.createCriteria(clazz);
		criteria.setProjection(Projections.rowCount());

		CriteriaProcessor processor = processors.get(this.clazz);
		if (processor != null) {
			processor.mergeCriteria(criteria, criterias);
		}

		return ((Long) criteria.uniqueResult());
	}

}
