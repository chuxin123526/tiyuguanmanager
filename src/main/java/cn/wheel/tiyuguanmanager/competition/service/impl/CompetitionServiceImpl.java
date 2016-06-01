package cn.wheel.tiyuguanmanager.competition.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.wheel.tiyuguanmanager.competition.dao.ICompetitionDao;
import cn.wheel.tiyuguanmanager.competition.po.Competition;
import cn.wheel.tiyuguanmanager.competition.service.ICompetitionService;

@Service
@Transactional
public class CompetitionServiceImpl implements ICompetitionService
{
	@Resource
	private ICompetitionDao competitionDaoImpl ; 

	public List<Competition> list() throws Exception
	{
		return competitionDaoImpl.list() ;
	}

	public List<Competition> pageList() throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void add(Competition entity) throws Exception
	{
		this.competitionDaoImpl.add(entity);
	}

	public void deleteById(long id) throws Exception
	{
		// TODO Auto-generated method stub
		
	}

	public void update(Competition entity) throws Exception
	{
		// TODO Auto-generated method stub
		
	}

	public Competition findById(long id) throws Exception
	{
		return this.competitionDaoImpl.findById(id) ;
	}

	public List<Competition> unApproveCompetitionList() throws Exception
	{
		return this.competitionDaoImpl.unApproveCompetitionList() ; 
	}

	public void approvePass(long competitionID) throws Exception
	{
		//获取原对象
		Competition competition = this.competitionDaoImpl.findById(competitionID) ; 
		String status = "已通过" ;
		//设置状态
		competition.setStatus(status) ; 
		//repository
		this.competitionDaoImpl.update(competition);
	}

	public void approveNotPass(long competitionID) throws Exception
	{
		//获取原对象
		Competition competition = this.competitionDaoImpl.findById(competitionID) ; 
		String status = "不通过" ;
		//设置状态
		competition.setStatus(status) ; 
		//repository
		this.competitionDaoImpl.update(competition);
	}
	
}
