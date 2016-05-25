package cn.wheel.tiyuguanmanager.competition.po;

/**
* @ClassName: CompetitionCost
* @Description: 赛事费用
* @author somebody
* @date 2016年5月24日 下午4:22:07
 */
public class CompetitionCost
{
	private long id ; 
	private int money ;
	private Competition competition ; //所属赛事 
	
	public long getId()
	{
		return id;
	}
	public void setId(long id)
	{
		this.id = id;
	}
	public int getMoney()
	{
		return money;
	}
	public void setMoney(int money)
	{
		this.money = money;
	} 
	
	
	
}
