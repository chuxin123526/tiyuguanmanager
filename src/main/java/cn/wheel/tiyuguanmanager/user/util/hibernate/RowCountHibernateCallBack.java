package cn.wheel.tiyuguanmanager.user.util.hibernate;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate4.HibernateCallback;

import cn.wheel.tiyuguanmanager.user.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.user.po.Role;
import cn.wheel.tiyuguanmanager.user.po.User;
import cn.wheel.tiyuguanmanager.user.util.hibernate.criteria.RoleCriteriaUtils;
import cn.wheel.tiyuguanmanager.user.util.hibernate.criteria.UserCriteriaUtils;

public class RowCountHibernateCallBack implements HibernateCallback<Long> {

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

		if (this.clazz == User.class) {
			UserCriteriaUtils.mergeCriteria(criteria, criterias);
		} else if (this.clazz == Role.class) {
			RoleCriteriaUtils.mergeCriteria(criteria, criterias);
		}

		return ((Long)criteria.uniqueResult());
	}

}
