package cn.wheel.tiyuguanmanager.competition.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import cn.wheel.tiyuguanmanager.competition.base.impl.BaseDaoImpl;
import cn.wheel.tiyuguanmanager.competition.dao.ICompetitionDao;
import cn.wheel.tiyuguanmanager.competition.po.Competition;

@Repository
public class CompetitionDaoImpl extends BaseDaoImpl<Competition> implements ICompetitionDao
{
	public List<Competition> unApproveCompetitionList() throws Exception
	{
		String hql = "from Competition where status=?" ; 
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql) ; 
		query.setString(1,"Œ¥…Û∫À") ; 
		List<Competition> unApproveCompetitionList = query.list() ; 
		
		return unApproveCompetitionList ; 
	}

	

}
