package cn.wheel.tiyuguanmanager.competition.dao;

import java.util.List;

import cn.wheel.tiyuguanmanager.competition.base.IBaseDao;
import cn.wheel.tiyuguanmanager.competition.po.Competition;

public interface ICompetitionDao extends IBaseDao<Competition>
{
	/**
	* ��������unApproveCompetitionList
	* ��������ȡδ��������б�
	* ������Ա��somebody
	* �޸���Ա: somebody 
	* ����ʱ�䣺2016��5��30�� 
	* @return
	* @throws Exception
	* @throws
	 */
	public List<Competition> unApproveCompetitionList() throws Exception ; 
	
}
