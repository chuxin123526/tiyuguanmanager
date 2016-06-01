package cn.wheel.tiyuguanmanager.competition.service;

import java.util.List;

import cn.wheel.tiyuguanmanager.competition.base.IBaseService;
import cn.wheel.tiyuguanmanager.competition.po.Competition;

public interface ICompetitionService extends IBaseService<Competition>
{
	/**
	* 方法名：unApproveCompetitionList
	* 详述：TODO
	* 开发人员：somebody
	* 修改人员: somebody 
	* 创建时间：2016年5月30日 
	* @return
	* @throws Exception
	* @throws
	 */
	public List<Competition> unApproveCompetitionList() throws Exception ; 
	
	/**
	* 方法名：approve
	* 详述：通过
	* 开发人员：somebody
	* 修改人员: somebody 
	* 创建时间：2016年5月30日 
	* @throws Exception
	* @throws
	 */
	public void approvePass(long competitionID) throws Exception ; 
	
	/**
	 * 
	* 方法名：不通过
	* 详述：TODO
	* 开发人员：somebody
	* 修改人员: somebody 
	* 创建时间：2016年5月31日 
	* @param competitionID
	* @throws Exception
	* @throws
	 */
	public void approveNotPass(long competitionID) throws Exception ; 
}
