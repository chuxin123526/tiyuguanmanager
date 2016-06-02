package cn.wheel.tiyuguanmanager.user.util.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateCallback;

import cn.wheel.tiyuguanmanager.user.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.user.po.Role;
import cn.wheel.tiyuguanmanager.user.util.SQLUtils;

public class RoleCriteriaHibernateCallback implements
		HibernateCallback<List<Role>> {

	private DaoCriteria[] criterias;

	public RoleCriteriaHibernateCallback(DaoCriteria[] criterias) {
		this.criterias = criterias;
	}

	public RoleCriteriaHibernateCallback setCriterias(DaoCriteria[] criterias) {
		this.criterias = criterias;
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Role> doInHibernate(Session session) throws HibernateException {
		if (this.criterias == null || this.criterias.length == 0) {
			return session.createCriteria(Role.class).list();
		} else {
			Criteria criteria = session.createCriteria(Role.class);

			for (DaoCriteria daoCriteria : this.criterias) {
				int op = daoCriteria.getOp();
				int type = daoCriteria.getType();

				switch (type) {
				case DaoCriteria.TYPE_ROLE_NAME:
					if (op == DaoCriteria.OP_EQUAL) {
						criteria.add(Restrictions.eq("name", daoCriteria
								.getContent().toString()));
					} else if (op == DaoCriteria.OP_LIKE) {
						criteria.add(Restrictions.like("name", SQLUtils
								.wrapLikeCriteria(daoCriteria.getContent()
										.toString())));
					}

					break;

				default:
					break;
				}
			}

			return criteria.list();
		}
	}

}
