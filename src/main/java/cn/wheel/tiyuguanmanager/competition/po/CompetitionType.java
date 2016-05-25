package cn.wheel.tiyuguanmanager.competition.po;

import java.util.HashSet;
import java.util.Set;

/**
 * 
* @ClassName: CompetitionType
* @Description: 赛事类型
* @author somebody
* @date 2016年5月24日 下午3:46:10
 */
public class CompetitionType
{
	private long id ; 
	private String name ; 
	private String description ;
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
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
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
