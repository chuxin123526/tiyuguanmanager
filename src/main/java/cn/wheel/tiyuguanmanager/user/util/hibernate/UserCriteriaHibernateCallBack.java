package cn.wheel.tiyuguanmanager.user.util.hibernate;

import org.hibernate.Criteria;

import cn.wheel.tiyuguanmanager.common.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.common.util.hibernate.PaginationHibernateCriteriaCallback;
import cn.wheel.tiyuguanmanager.user.po.User;
import cn.wheel.tiyuguanmanager.user.util.hibernate.criteria.UserCriteriaUtils;

/**
 * 用户动态查询
 * 
 * @author DENG YURONG
 * 
 */
public class UserCriteriaHibernateCallBack extends PaginationHibernateCriteriaCallback<User> {
	private static UserCriteriaUtils criteriaUtils;
	
	static {
		criteriaUtils = new UserCriteriaUtils();
	}
	
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
			criteriaUtils.mergeCriteria(criteria, criterias);
		}
	}
}
