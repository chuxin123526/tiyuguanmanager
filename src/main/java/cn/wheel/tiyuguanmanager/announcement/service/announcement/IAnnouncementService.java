package cn.wheel.tiyuguanmanager.announcement.service.announcement;

import cn.wheel.tiyuguanmanager.announcement.exception.AnnouncementNotFoundException;
import cn.wheel.tiyuguanmanager.announcement.exception.SpecifiedAnnouncementIsNotDraftException;
import cn.wheel.tiyuguanmanager.announcement.vo.AnnouncementVO;
import cn.wheel.tiyuguanmanager.common.exception.FormException;
import cn.wheel.tiyuguanmanager.user.exception.UserNotExistException;

public interface IAnnouncementService {
	/**
	 * ����һ���µĹ���
	 * 
	 * @param announcementVO
	 *            ���й�����Ϣ�ı�
	 * @throws UserNotExistException
	 *             �������ָ���û��ı��û�ж�Ӧ���û���¼������׳�����쳣
	 * @throws FormException
	 *             ��У���쳣������֮
	 */
	public void publishNewAnnouncement(AnnouncementVO announcementVO) throws UserNotExistException, FormException;

	/**
	 * ����һ������ݸ�
	 * 
	 * @param announcementVO
	 *            ���й�����Ϣ�ı�
	 * @throws UserNotExistException
	 *             �������ָ���û��ı��û�ж�Ӧ���û���¼������׳�����쳣
	 */
	public void saveNewAnnouncementDraft(AnnouncementVO announcementVO) throws UserNotExistException;

	/**
	 * ��һ���ݸ巢��
	 * 
	 * @param draftAnnouncementVO
	 *            ���вݸ����
	 * @throws UserNotExistException
	 *             �������ָ���û��ı��û�ж�Ӧ���û���¼������׳�����쳣
	 */
	public void publishDraft(AnnouncementVO draftAnnouncementVO) throws UserNotExistException, SpecifiedAnnouncementIsNotDraftException,
			AnnouncementNotFoundException;

	/**
	 * ��ʱ�䵹���г����й���
	 */
	public void listLatestAnnouncement();

	/**
	 * �г����µ� n ������
	 * 
	 * @param count
	 *            �г��Ĺ�������
	 */
	public void listLateseAnnouncement(int count);

	/**
	 * ɾ������
	 * 
	 * @param announcementId
	 *            Ҫɾ���Ĺ�����
	 * @throws AnnouncementNotFoundException
	 *             ���������û�ж�Ӧ��Ч�Ĺ��棬����׳�����쳣
	 */
	public void deleteAnnouncement(long announcementId) throws AnnouncementNotFoundException;

	/**
	 * �޸Ĺ���
	 * 
	 * @param announcementVO
	 *            �����
	 * @throws AnnouncementNotFoundException
	 *             ���������û�ж�Ӧ��Ч�Ĺ��棬����׳�����쳣
	 */
	public void updateAnnouncement(AnnouncementVO announcementVO) throws AnnouncementNotFoundException;
}
