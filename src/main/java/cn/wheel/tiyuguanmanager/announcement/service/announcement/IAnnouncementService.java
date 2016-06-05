package cn.wheel.tiyuguanmanager.announcement.service.announcement;

import cn.wheel.tiyuguanmanager.announcement.exception.AnnouncementNotFoundException;
import cn.wheel.tiyuguanmanager.announcement.exception.SpecifiedAnnouncementIsNotDraftException;
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
	public void listLatestAnnouncement();

	/**
	 * 列出最新的 n 条公告
	 * 
	 * @param count
	 *            列出的公告数量
	 */
	public void listLateseAnnouncement(int count);

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
	 */
	public void updateAnnouncement(AnnouncementVO announcementVO) throws AnnouncementNotFoundException;
}
