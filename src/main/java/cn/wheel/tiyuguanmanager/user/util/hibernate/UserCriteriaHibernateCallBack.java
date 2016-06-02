package cn.wheel.tiyuguanmanager.user.util.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate4.HibernateCallback;

import cn.wheel.tiyuguanmanager.user.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.user.po.User;

/**
 * 用户动态查询
 * 
 * @author DENG YURONG
 * 
 */
public class UserCriteriaHibernateCallBack implements
		HibernateCallback<List<User>> {

	private DaoCriteria[] criterias;

	public UserCriteriaHibernateCallBack(DaoCriteria[] criterias) {
		this.criterias = criterias;
	}

	public UserCriteriaHibernateCallBack setCriterias(DaoCriteria[] criterias) {
		this.criterias = criterias;
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> doInHibernate(Session session) throws HibernateException {
		if (criterias == null || criterias.length == 0) {
			return session.createCriteria(User.class).list();
		} else {
			Criteria criteria = session.createCriteria(User.class);

			for (DaoCriteria daoCriteria : this.criterias) {
				int type = daoCriteria.getType();
				int op = daoCriteria.getOp();

				switch (type) {
				case DaoCriteria.TYPE_USER_USERNAME:
					if (op == DaoCriteria.OP_EQUAL) {
						criteria.add(Restrictions.eq("username", daoCriteria
								.getContent().toString()));
					} else if (op == DaoCriteria.OP_LIKE) {
						criteria.add(Restrictions.like("username", "%"
								+ daoCriteria.getContent().toString() + "%"));
					}
					break;

				case DaoCriteria.TYPE_USER_PASSWORD:
					if (op == DaoCriteria.OP_EQUAL) {
						criteria.add(Restrictions.eq("password", daoCriteria
								.getContent().toString()));
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
