package cn.wheel.tiyuguanmanager.common.dao.criteria;

import org.hibernate.Criteria;

public interface CriteriaProcessor {
	public void mergeCriteria(Criteria criteria, DaoCriteria[] daoCriterias);
	
	public boolean canProcess(Class<? extends Object> clazz);
}
