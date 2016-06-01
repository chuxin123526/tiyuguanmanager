package cn.wheel.tiyuguanmanager.competition.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.wheel.tiyuguanmanager.competition.dao.ICompetitionUserDao;
import cn.wheel.tiyuguanmanager.competition.po.Competition;
import cn.wheel.tiyuguanmanager.competition.po.CompetitionUser;
import cn.wheel.tiyuguanmanager.competition.service.ICompetitionService;
import cn.wheel.tiyuguanmanager.competition.service.ICompetitionUserService;

@Service
@Transactional
public class CompetitionUserServiceImpl implements ICompetitionUserService
{
	@Resource
	private ICompetitionUserDao competitionUserDaoImpl ;

	public List<CompetitionUser> list() throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	public List<CompetitionUser> pageList() throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void add(CompetitionUser entity) throws Exception
	{
		// TODO Auto-generated method stub
		
	}

	public void deleteById(long id) throws Exception
	{
		// TODO Auto-generated method stub
		
	}

	public void update(CompetitionUser entity) throws Exception
	{
		// TODO Auto-generated method stub
		
	}

	public CompetitionUser findById(long id) throws Exception
	{
		return this.competitionUserDaoImpl.findById(id) ;
	} 
	
}
