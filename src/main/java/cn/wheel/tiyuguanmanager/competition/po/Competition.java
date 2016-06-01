package cn.wheel.tiyuguanmanager.competition.po;

import java.util.Date;

import cn.wheel.tiyuguanmanager.somebodyTest.User;

/**
* @ClassName: Competition
* @Description: 赛事
* @author somebody
* @date 2016年5月24日 下午3:47:12
 */
public class Competition
{
	private long id ; 
	private String applyUserName ; 
	private String name ;
	private String description ; 
	private String mainTeam ; 
	private String guestTeam ; 
	private Date beginTime ; 
	private Date endTime ; 
	private String status ; 
	private int mainScore ; 
	private int guestScore ; 
	private int recommend ; 
	private String comment ; 
	
	private CompetitionUser competitionUser ; 
	private CompetitionType competitionType ; //所属赛事类型
	private CompetitionCost competitionCost ; //该赛事费用

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public CompetitionUser getCompetitionUser()
	{
		return competitionUser;
	}

	public void setCompetitionUser(CompetitionUser competitionUser)
	{
		this.competitionUser = competitionUser;
	}

	public String getDescription()
	{
		return description;
	}
	
	public String getApplyUserName()
	{
		return applyUserName;
	}

	public void setApplyUserName(String applyUserName)
	{
		this.applyUserName = applyUserName;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getMainTeam()
	{
		return mainTeam;
	}

	public void setMainTeam(String mainTeam)
	{
		this.mainTeam = mainTeam;
	}

	public String getGuestTeam()
	{
		return guestTeam;
	}

	public void setGuestTeam(String guestTeam)
	{
		this.guestTeam = guestTeam;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}

	public Date getBeginTime()
	{
		return beginTime;
	}

	public void setBeginTime(Date beginTime)
	{
		this.beginTime = beginTime;
	}

	public Date getEndTime()
	{
		return endTime;
	}

	public void setEndTime(Date endTime)
	{
		this.endTime = endTime;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}
	

	public CompetitionCost getCompetitionCost()
	{
		return competitionCost;
	}

	public void setCompetitionCost(CompetitionCost competitionCost)
	{
		this.competitionCost = competitionCost;
	}

	public int getMainScore()
	{
		return mainScore;
	}

	public void setMainScore(int mainScore)
	{
		this.mainScore = mainScore;
	}

	public int getGuestScore()
	{
		return guestScore;
	}

	public void setGuestScore(int guestScore)
	{
		this.guestScore = guestScore;
	}

	public int getRecommend()
	{
		return recommend;
	}

	public void setRecommend(int recommend)
	{
		this.recommend = recommend;
	}

	public CompetitionType getCompetitionType()
	{
		return competitionType;
	}

	public void setCompetitionType(CompetitionType competitionType)
	{
		this.competitionType = competitionType;
	}
	
	
	
}
