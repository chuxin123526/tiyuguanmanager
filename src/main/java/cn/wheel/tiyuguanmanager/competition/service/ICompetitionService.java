package cn.wheel.tiyuguanmanager.competition.service;

import java.util.List;

import cn.wheel.tiyuguanmanager.competition.base.IBaseService;
import cn.wheel.tiyuguanmanager.competition.po.Competition;

public interface ICompetitionService extends IBaseService<Competition>
{
	/**
	* ��������unApproveCompetitionList
	* ������TODO
	* ������Ա��somebody
	* �޸���Ա: somebody 
	* ����ʱ�䣺2016��5��30�� 
	* @return
	* @throws Exception
	* @throws
	 */
	public List<Competition> unApproveCompetitionList() throws Exception ; 
	
	/**
	* ��������approve
	* ������ͨ��
	* ������Ա��somebody
	* �޸���Ա: somebody 
	* ����ʱ�䣺2016��5��30�� 
	* @throws Exception
	* @throws
	 */
	public void approvePass(long competitionID) throws Exception ; 
	
	/**
	 * 
	* ����������ͨ��
	* ������TODO
	* ������Ա��somebody
	* �޸���Ա: somebody 
	* ����ʱ�䣺2016��5��31�� 
	* @param competitionID
	* @throws Exception
	* @throws
	 */
	public void approveNotPass(long competitionID) throws Exception ; 
}
