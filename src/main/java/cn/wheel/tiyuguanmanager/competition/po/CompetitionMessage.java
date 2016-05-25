package cn.wheel.tiyuguanmanager.competition.po;

import java.util.Date;
/**
* @ClassName: CompetitionMessage
* @Description: 消息
* @author somebody
* @date 2016年5月24日 下午4:22:25
 */
public class CompetitionMessage
{
	private long id ; 
	private Date addTime ; 
	private String content ;
	
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
	
	
	
}
