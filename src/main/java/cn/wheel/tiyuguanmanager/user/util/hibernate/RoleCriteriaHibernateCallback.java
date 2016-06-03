package cn.wheel.tiyuguanmanager.user.util.hibernate;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import cn.wheel.tiyuguanmanager.user.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.user.po.Role;
import cn.wheel.tiyuguanmanager.user.util.SQLUtils;

public class RoleCriteriaHibernateCallback extends PaginationHibernateCriteriaCallback<Role> {

	private DaoCriteria[] criterias;

	public RoleCriteriaHibernateCallback(DaoCriteria[] criterias) {
		super(Role.class);

		this.criterias = criterias;
	}

	public RoleCriteriaHibernateCallback setCriterias(DaoCriteria[] criterias) {
		this.criterias = criterias;
		return this;
	}

	@Override
	public void doProcessCriteria(Criteria criteria) {
		if (this.criterias != null && this.criterias.length > 0) {
			for (DaoCriteria daoCriteria : this.criterias) {
				int op = daoCriteria.getOp();
				int type = daoCriteria.getType();

				switch (type) {
				case DaoCriteria.TYPE_ROLE_NAME:
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
