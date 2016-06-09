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
	public List<Announcement> listLatestAnnouncement();

	/**
	 * �г����µ� n ������
	 * 
	 * @param count
	 *            �г��Ĺ�������
	 */
	public List<Announcement> listLateseAnnouncement(int count);

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
	 * @throws UserNotExistException
	 *             ���ָ�����û���Ч������׳�����쳣
	 */
	public void updateAnnouncement(AnnouncementVO announcementVO) throws AnnouncementNotFoundException, FormException, UserNotExistException;

	/**
	 * ���ݸ�����������ѯ��������
	 * 
	 * @param queryVO
	 *            ��ѯ����������
	 * @param page
	 *            ҳ��
	 * @return ���
	 */
	public AnnouncementQueryResult queryAnnouncement(AnnouncementQueryVO queryVO, int page);

	/**
	 * ���ݸ����Ĺ�����Ѱ�ҹ���ʵ��
	 * 
	 * @param announcementId
	 *            ������
	 * @return ������ݿ��д�����Ӧ�Ĺ���ʵ�壬�򷵻أ����򷵻� null
	 */
	public Announcement findAnnouncementById(long announcementId);

	/**
	 * ���ڻ�ù����Ĺ����б�
	 * 
	 * @param page
	 *            ҳ��
	 * @param countPerPage
	 *            ÿһҳ�Ĺ�������
	 * @return ���й������ݵĽ������
	 */
	public AnnouncementQueryResult publicAnnouncementList(int page, int countPerPage);

	/**
	 * ���ݹ������ݺͱ����ѯ�Թ�����в���
	 * 
	 * @param keyword
	 *            Ҫ���ҵĹؼ���
	 * @param page
	 *            ҳ��
	 * @return ���в�ѯ����Լ�����������Ϣ�Ľ����
	 */
	public AnnouncementQueryResult keywordSearch(String keyword, int page);

	/**
	 * �ָ��Ѿ�ɾ���Ĺ���
	 * 
	 * @param announcementId
	 *            Ҫ�ָ��Ĺ�����
	 * @throws AnnouncementNotFoundException
	 *             ���������ָ���Ĺ�����󲻴�������׳�����쳣
	 */
	public void recoverAnnouncement(long announcementId) throws AnnouncementNotFoundException;
}
