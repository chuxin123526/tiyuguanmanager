package cn.wheel.tiyuguanmanager.competition.po;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CompetitionNews
{
	private long id ; 
	private Date addTime ; 
	private String title ; 
	private String content ;
	
	private Set<CompetitionNewsComment> competitionNewsComments = 
			new HashSet<CompetitionNewsComment>() ; //ÐÂÎÅÆÀÂÛ 
	
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public Date getAddTime()
	{
		return addTime;
	}
	public void setAddTime(Date addTime)
	{
		this.addTime = addTime;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public Set<CompetitionNewsComment> getCompetitionNewsComments()
	{
		return competitionNewsComments;
	}
	public void setCompetitionNewsComments(Set<CompetitionNewsComment> competitionNewsComments)
	{
		this.competitionNewsComments = competitionNewsComments;
	} 
	
	
	
}
