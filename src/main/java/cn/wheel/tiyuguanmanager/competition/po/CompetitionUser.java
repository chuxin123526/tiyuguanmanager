package cn.wheel.tiyuguanmanager.competition.po;

import java.util.HashSet;
import java.util.Set;

public class CompetitionUser
{
	private long id ; 
	private String name ; 
	
	private Set<Competition> competitions = new HashSet<Competition>() ;

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

	public Set<Competition> getCompetitions()
	{
		return competitions;
	}

	public void setCompetitions(Set<Competition> competitions)
	{
		this.competitions = competitions;
	} 
	
	
}
