package cn.wheel.tiyuguanmanager.competition.po;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class CompetitionNewsComment
{
	private long id ; 
	private Date addTime ; 
	private String content ;
	
	private CompetitionNews competitionNews ; //��������������
	private Set<CompetitionNewsComment> competitionNewsComments =
			new HashSet<CompetitionNewsComment>() ; //�������µ����лظ� 
	
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
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public CompetitionNews getCompetitionNews()
	{
		return competitionNews;
	}
	public void setCompetitionNews(CompetitionNews competitionNews)
	{
		this.competitionNews = competitionNews;
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
