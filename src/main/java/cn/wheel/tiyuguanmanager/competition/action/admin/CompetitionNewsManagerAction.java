package cn.wheel.tiyuguanmanager.competition.action.admin;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.wheel.tiyuguanmanager.competition.po.CompetitionNews;
import cn.wheel.tiyuguanmanager.competition.service.ICompetitionNewsService;
import cn.wheel.tiyuguanmanager.competition.service.ICompetitionService;

@Controller
@Scope("prototype")
public class CompetitionNewsManagerAction extends ActionSupport
{
	private CompetitionNews competitionNews ; 
	
	@Resource
	private ICompetitionNewsService competitionNewsServiceImpl ;
	
	public String addUI()
	{
		return "addUI" ;
	}
	
	public String add() 
	{
		try
		{
			this.competitionNews.setAddTime(new Date());
			this.competitionNewsServiceImpl.add(this.competitionNews);
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return "error" ;
		}
		
		return "toList" ; 
	}
	
	public String list() 
	{
		try
		{
			List<CompetitionNews> competitionNewsList = this.competitionNewsServiceImpl.list() ; 
			for(CompetitionNews competitionNews : competitionNewsList)
			{
				String content = competitionNews.getContent() ; 
				content = content.substring(0, 200) ;
				competitionNews.setContent(content); 
			}
			
			ServletActionContext.getContext().put("competitionNewsList", competitionNewsList) ;
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return "error" ; 
		}
		
		return "list" ;
	}

	public CompetitionNews getCompetitionNews()
	{
		return competitionNews;
	}

	public void setCompetitionNews(CompetitionNews competitionNews)
	{
		this.competitionNews = competitionNews;
	} 
	
	
	
}
