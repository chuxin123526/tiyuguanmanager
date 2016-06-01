package cn.wheel.tiyuguanmanager.competition.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.wheel.tiyuguanmanager.competition.po.Competition;
import cn.wheel.tiyuguanmanager.competition.po.CompetitionUser;
import cn.wheel.tiyuguanmanager.competition.service.ICompetitionService;

/**
 * 
* @ClassName: CompetitionAction
* @Description: competitionAction
* @author somebody
* @date 2016年5月30日 下午3:20:27
 */
@Controller
@Scope("prototype")
public class CompetitionAction extends ActionSupport
{
	@Resource
	private ICompetitionService competitionServiceImpl ; 
	
	private String beginTime ; 
	private String endTime ; 
	
	
	private Competition competition ; 
	
	public String index() 
	{
		return "index" ; 
	}
	
	public String applyUI() 
	{
		return "applyUI" ; 
	}
	
	public String apply()
	{
		try
		{
			CompetitionUser competitionUser = (CompetitionUser)
					ServletActionContext.getContext().getSession().get("user") ; 
			this.competition.setStatus("未审核");
			
			//日期转换
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm") ; 
			Date beginTime = simpleDateFormat.parse(this.beginTime) ;
			Date endTime = simpleDateFormat.parse(this.endTime) ; 
			//设置开始结束时间
			this.competition.setBeginTime(beginTime) ;
			this.competition.setEndTime(endTime) ;
			
			
			this.competition.setCompetitionUser(competitionUser);
			this.competitionServiceImpl.add(this.competition);
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return "error" ; 
		}
		
		return "toApplying" ; 
	}
	
	public String applying() 
	{
		return "applying" ; 
	}
	
	public String unStart() 
	{
		return "unStart" ; 
	}
	
	public String ongoing() 
	{
		return "ongoing" ; 
	}
	
	public String end() 
	{
		return "end" ; 
	}
	
	public String unApproveCompetitionList() 
	{
		try
		{
			List<Competition> unApproveCompetitionList = this.competitionServiceImpl.unApproveCompetitionList() ;
			ServletActionContext.getContext().put("unApproveCompetitionList", unApproveCompetitionList) ;
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return "error" ;
		} 
		
		return "unApproveCompetitionList" ; 
	}
	
	
	public Competition getCompetition()
	{
		return competition;
	}

	public void setCompetition(Competition competition)
	{
		this.competition = competition;
	}

	public String getBeginTime()
	{
		return beginTime;
	}

	public void setBeginTime(String beginTime)
	{
		this.beginTime = beginTime;
	}

	public String getEndTime()
	{
		return endTime;
	}

	public void setEndTime(String endTime)
	{
		this.endTime = endTime;
	}

	

}
