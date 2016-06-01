package cn.wheel.tiyuguanmanager.competition.dao;

import java.util.List;

import cn.wheel.tiyuguanmanager.competition.base.IBaseDao;
import cn.wheel.tiyuguanmanager.competition.po.Competition;

public interface ICompetitionDao extends IBaseDao<Competition>
{
	/**
	* 方法名：unApproveCompetitionList
	* 详述：获取未审核赛事列表
	* 开发人员：somebody
	* 修改人员: somebody 
	* 创建时间：2016年5月30日 
	* @return
	* @throws Exception
	* @throws
	 */
	public List<Competition> unApproveCompetitionList() throws Exception ; 
	
}
