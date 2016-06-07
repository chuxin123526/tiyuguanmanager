package cn.wheel.tiyuguanmanager.announcement.service.announcement;

import java.util.List;

import cn.wheel.tiyuguanmanager.announcement.exception.AnnouncementNotFoundException;
import cn.wheel.tiyuguanmanager.announcement.exception.SpecifiedAnnouncementIsNotDraftException;
import cn.wheel.tiyuguanmanager.announcement.po.Announcement;
import cn.wheel.tiyuguanmanager.announcement.vo.AnnouncementQueryResult;
import cn.wheel.tiyuguanmanager.announcement.vo.AnnouncementQueryVO;
import cn.wheel.tiyuguanmanager.announcement.vo.AnnouncementVO;
import cn.wheel.tiyuguanmanager.common.exception.FormException;
import cn.wheel.tiyuguanmanager.user.exception.UserNotExistException;

public interface IAnnouncementService {
	/**
	 * 发布一条新的公告
	 * 
	 * @param announcementVO
	 *            含有公告信息的表单
	 * @throws UserNotExistException
	 *             如果表单中指定用户的编号没有对应的用户记录，则会抛出这个异常
	 * @throws FormException
	 *             表单校验异常，则抛之
	 */
	public void publishNewAnnouncement(AnnouncementVO announcementVO) throws UserNotExistException, FormException;

	/**
	 * 保存一条公告草稿
	 * 
	 * @param announcementVO
	 *            含有公告信息的表单
	 * @throws UserNotExistException
	 *             如果表单中指定用户的编号没有对应的用户记录，则会抛出这个异常
	 */
	public void saveNewAnnouncementDraft(AnnouncementVO announcementVO) throws UserNotExistException;

	/**
	 * 把一条草稿发布
	 * 
	 * @param draftAnnouncementVO
	 *            含有草稿号码
	 * @throws UserNotExistException
	 *             如果表单中指定用户的编号没有对应的用户记录，则会抛出这个异常
	 */
	public void publishDraft(AnnouncementVO draftAnnouncementVO) throws UserNotExistException, SpecifiedAnnouncementIsNotDraftException,
			AnnouncementNotFoundException;

	/**
	 * 按时间倒序列出所有公告
	 */
	public List<Announcement> listLatestAnnouncement();

	/**
	 * 列出最新的 n 条公告
	 * 
	 * @param count
	 *            列出的公告数量
	 */
	public List<Announcement> listLateseAnnouncement(int count);

	/**
	 * 删除公告
	 * 
	 * @param announcementId
	 *            要删除的公告编号
	 * @throws AnnouncementNotFoundException
	 *             如果这个编号没有对应有效的公告，则会抛出这个异常
	 */
	public void deleteAnnouncement(long announcementId) throws AnnouncementNotFoundException;

	/**
	 * 修改公告
	 * 
	 * @param announcementVO
	 *            公告表单
	 * @throws AnnouncementNotFoundException
	 *             如果这个编号没有对应有效的公告，则会抛出这个异常
	 * @throws UserNotExistException
	 *             如果指定的用户无效，则会抛出这个异常
	 */
	public void updateAnnouncement(AnnouncementVO announcementVO) throws AnnouncementNotFoundException, FormException, UserNotExistException;

	/**
	 * 根据给定的条件查询公告数据
	 * 
	 * @param queryVO
	 *            查询公告条件表单
	 * @param page
	 *            页码
	 * @return 结果
	 */
	public AnnouncementQueryResult queryAnnouncement(AnnouncementQueryVO queryVO, int page);

	/**
	 * 根据给定的公告编号寻找公告实体
	 * 
	 * @param announcementId
	 *            公告编号
	 * @return 如果数据库中存在相应的公告实体，则返回；否则返回 null
	 */
	public Announcement findAnnonucementById(long announcementId);
}
