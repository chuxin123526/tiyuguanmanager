package cn.wheel.tiyuguanmanager.user.util.hibernate;

import org.hibernate.Criteria;

import cn.wheel.tiyuguanmanager.common.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.common.util.hibernate.PaginationHibernateCriteriaCallback;
import cn.wheel.tiyuguanmanager.user.po.Role;
import cn.wheel.tiyuguanmanager.user.util.hibernate.criteria.RoleCriteriaUtils;

public class RoleCriteriaHibernateCallback extends PaginationHibernateCriteriaCallback<Role> {

	private static RoleCriteriaUtils criteriaUtils;
	
	static {
		criteriaUtils = new RoleCriteriaUtils();
	}
	
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
			criteriaUtils.mergeCriteria(criteria, criterias);
		}
	}

}
