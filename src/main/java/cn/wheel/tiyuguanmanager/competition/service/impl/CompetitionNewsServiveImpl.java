package cn.wheel.tiyuguanmanager.competition.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import cn.wheel.tiyuguanmanager.competition.dao.ICompetitionNewsDao;
import cn.wheel.tiyuguanmanager.competition.po.Competition;
import cn.wheel.tiyuguanmanager.competition.po.CompetitionNews;
import cn.wheel.tiyuguanmanager.competition.service.ICompetitionNewsService;
import cn.wheel.tiyuguanmanager.competition.service.ICompetitionService;

@Service
@Transactional
public class CompetitionNewsServiveImpl implements ICompetitionNewsService
{
	@Resource
	private ICompetitionNewsDao competitionNewsDaoImpl ;

	public List<CompetitionNews> list() throws Exception
	{
		return this.competitionNewsDaoImpl.list() ;
	}

	public List<CompetitionNews> pageList() throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void add(CompetitionNews entity) throws Exception
	{
		this.competitionNewsDaoImpl.add(entity);
	}

	public void deleteById(long id) throws Exception
	{
		// TODO Auto-generated method stub
		
	}

	public void update(CompetitionNews entity) throws Exception
	{
		// TODO Auto-generated method stub
		
	}

	public CompetitionNews findById(long id) throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	} 


}
