package cn.wheel.tiyuguanmanager.competition.action;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.transaction.Transactional;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.wheel.tiyuguanmanager.competition.po.CompetitionUser;
import cn.wheel.tiyuguanmanager.competition.service.ICompetitionUserService;

@Controller
@Scope("prototype")
public class CompetitionUserAction
{
	@Resource
	private ICompetitionUserService competitionUserServiceImpl ; 
	
	private CompetitionUser competitionUser ;
	
	public String login() 
	{
		try
		{
			this.competitionUser = this.competitionUserServiceImpl.
			findById(this.competitionUser.getId()) ;
			if(this.competitionUser == null)
			{
				return "userNotFound" ;
			}
			
			ServletActionContext.getContext()
			.put("user", this.competitionUser);
		} catch (Exception e)
		{
			e.printStackTrace();
		} 
		
		return "toCompetitionIndex" ; 
	}

	public CompetitionUser getCompetitionUser()
	{
		return competitionUser;
	}

	public void setCompetitionUser(CompetitionUser competitionUser)
	{
		this.competitionUser = competitionUser;
	} 
	
	
	
	
}
