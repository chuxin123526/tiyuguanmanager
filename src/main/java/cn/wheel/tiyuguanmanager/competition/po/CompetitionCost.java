package cn.wheel.tiyuguanmanager.competition.po;

/**
* @ClassName: CompetitionCost
* @Description: ���·���
* @author somebody
* @date 2016��5��24�� ����4:22:07
 */
public class CompetitionCost
{
	private long id ; 
	private int money ;
	private Competition competition ; //�������� 
	
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
