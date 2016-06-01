package cn.wheel.tiyuguanmanager.competition.action.admin;

import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import cn.wheel.tiyuguanmanager.competition.po.Competition;
import cn.wheel.tiyuguanmanager.competition.service.ICompetitionService;

@Controller
@Scope("prototype")
public class CompetitionManagerAction extends ActionSupport
{
	private Competition competition ; 
	private String isPass ; 
	
	@Resource
	private ICompetitionService competitionServiceImpl ; 
	
	public String list()
	{
		try
		{
			List<Competition> competitionList = this.competitionServiceImpl.list() ;
			ServletActionContext.getContext().put("competitionList", competitionList);
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return "error" ; 
		}
		
		return "list" ; 
	}
	
	/**
	 * 
	* 方法名：approveUI
	* 详述：跳转到审核页面
	* 开发人员：somebody
	* 修改人员: somebody 
	* 创建时间：2016年5月30日 
	* @return
	* @throws
	 */
	public String approveUI()
	{
		try
		{
			this.competition = this.competitionServiceImpl.findById(this.competition.getId()) ; 
			ServletActionContext.getContext().put("competition", this.competition) ;
			
		} catch (Exception e)
		{
			e.printStackTrace();
			return "error" ;
		}
		return "approveUI" ;
		
	}
	
	public String approve()
	{
		try
		{
			if(this.isPass.equals("yes"))
			{
				this.competitionServiceImpl.approvePass(this.competition.getId());
			}
			else 
				if(this.isPass.equals("no"))
				{
					this.competitionServiceImpl.approveNotPass(this.competition.getId());
				}
			
				
		} catch (Exception e)
		
		{
			e.printStackTrace();
			return "error" ;
		}
		return "toList" ;
	}

	public Competition getCompetition()
	{
		return competition;
	}

	public void setCompetition(Competition competition)
	{
		this.competition = competition;
	}

	public String getIsPass()
	{
		return isPass;
	}

	public void setIsPass(String isPass)
	{
		this.isPass = isPass;
	}
	
	
	
	
	
}
