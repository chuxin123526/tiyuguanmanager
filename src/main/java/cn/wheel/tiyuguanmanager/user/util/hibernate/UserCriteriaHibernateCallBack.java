package cn.wheel.tiyuguanmanager.user.util.hibernate;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import cn.wheel.tiyuguanmanager.user.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.user.po.User;
import cn.wheel.tiyuguanmanager.user.util.SQLUtils;

/**
 * 用户动态查询
 * 
 * @author DENG YURONG
 * 
 */
public class UserCriteriaHibernateCallBack extends PaginationHibernateCriteriaCallback<User> {
	private DaoCriteria[] criterias;

	public UserCriteriaHibernateCallBack(DaoCriteria[] criterias) {
		super(User.class);
		this.criterias = criterias;
	}

	public UserCriteriaHibernateCallBack setCriterias(DaoCriteria[] criterias) {
		this.criterias = criterias;
		return this;
	}

	@Override
	public void doProcessCriteria(Criteria criteria) {
		if (this.criterias != null && this.criterias.length > 0) {
			for (DaoCriteria daoCriteria : this.criterias) {
				int type = daoCriteria.getType();
				int op = daoCriteria.getOp();

				switch (type) {
				case DaoCriteria.TYPE_USER_USERNAME:
					if (op == DaoCriteria.OP_EQUAL) {
						criteria.add(Restrictions.eq("username", daoCriteria.getContent().toString()));
					} else if (op == DaoCriteria.OP_LIKE) {
						criteria.add(Restrictions.like("username", "%" + daoCriteria.getContent().toString() + "%"));
					}
					break;

				case DaoCriteria.TYPE_USER_PASSWORD:
					if (op == DaoCriteria.OP_EQUAL) {
						criteria.add(Restrictions.eq("password", daoCriteria.getContent().toString()));
					}
					break;
				case DaoCriteria.TYPE_USER_ROLE_NAME:
					criteria = criteria.createCriteria("role");

					if (op == DaoCriteria.OP_EQUAL) {
						criteria.add(Restrictions.eq("name", daoCriteria.getContent().toString()));
					} else if (op == DaoCriteria.OP_LIKE) {
						criteria.add(Restrictions.like("name", SQLUtils.wrapLikeCriteria(daoCriteria.getContent().toString())));
					}
					break;

				default:
					break;
				}
			}
		}
	}
}
