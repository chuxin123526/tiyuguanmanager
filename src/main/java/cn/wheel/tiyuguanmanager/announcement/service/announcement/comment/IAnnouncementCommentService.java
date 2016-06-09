package cn.wheel.tiyuguanmanager.announcement.service.announcement.comment;

import cn.wheel.tiyuguanmanager.announcement.exception.AnnouncementCommentNotFoundException;
import cn.wheel.tiyuguanmanager.announcement.exception.AnnouncementNotFoundException;
import cn.wheel.tiyuguanmanager.announcement.exception.CommentDeletePermissionDeniedException;
import cn.wheel.tiyuguanmanager.announcement.vo.comment.AnnouncementCommentQueryResult;
import cn.wheel.tiyuguanmanager.announcement.vo.comment.AnnouncementCommentQueryVO;
import cn.wheel.tiyuguanmanager.announcement.vo.comment.AnnouncementCommentVO;
import cn.wheel.tiyuguanmanager.user.exception.UserNotExistException;

public interface IAnnouncementCommentService {

	/**
	 * ���ָ�������Ŷ�Ӧ��������۵�����
	 * 
	 * @param announcementId
	 *            Ҫ������������Ĺ��湫����
	 * @return �ù��������۵�����
	 */
	public long getCommentCountOfAnnouncement(long announcementId);

	/**
	 * ���ݹ����ź�ҳ���г���������
	 * 
	 * @param announcementId
	 *            ������
	 * @param page
	 *            ҳ��
	 * @throws AnnouncementNotFoundException
	 *             ���ָ����Ŷ�Ӧ�Ĺ���ʵ�岻���ڣ�����׳�����쳣
	 * @return ���
	 */
	public AnnouncementCommentQueryResult listPublishedComment(long announcementId, int page) throws AnnouncementNotFoundException;

	/**
	 * ����һ����������
	 * 
	 * @param commentVO
	 *            ����Ӧ���б����۹���ı�ţ����������û��ı�ź���������
	 * @throws UserNotExistException
	 *             ��������û����ָ�����û������ڣ�����׳�����쳣
	 * @throws AnnouncementNotFoundException
	 *             ������������۱���ָ���Ĺ��棬����׳�����쳣
	 */
	public void publishComment(AnnouncementCommentVO commentVO) throws UserNotExistException, AnnouncementNotFoundException;

	/**
	 * ���ݱ��в�ѯ���������ݿ��в�ѯ��������������ʵ����
	 * 
	 * @param queryVO
	 *            ��
	 * @return ���
	 * @throws AnnouncementNotFoundException
	 *             ���ָ���Ĺ�������Ч������׳�����쳣
	 */
	public AnnouncementCommentQueryResult query(AnnouncementCommentQueryVO queryVO) throws AnnouncementNotFoundException;

	/**
	 * ��������ĳһ����������
	 * 
	 * @param commentId
	 *            �������۵ı��
	 * @throws AnnouncementNotFoundException
	 *             ���ָ����Ŷ�Ӧ�Ĺ������۲����ڣ�����׳�����쳣
	 */
	public void forbidAnnouncementComment(long commentId) throws AnnouncementNotFoundException;

	/**
	 * ������ʾһ���Ѿ������صĹ�������
	 * 
	 * @param commentId
	 *            �������۵ı��
	 * @throws AnnouncementCommentNotFoundException
	 *             ���ָ����Ŷ�Ӧ�Ĺ������۲����ڣ�����׳�����쳣
	 */
	public void showAnnouncementComment(long commentId) throws AnnouncementCommentNotFoundException;

	/**
	 * �����ض��Ĺ����ź�ָ���ķ��ػ�øù��������
	 * 
	 * @param announcementId
	 *            ��������
	 * @param from
	 *            ����һ�����ۿ�ʼ��ȡ
	 * @param count
	 *            ��ȡ������
	 * @return �����������Լ������Ϣ�Ķ�����
	 */
	public AnnouncementCommentQueryResult getAnnouncementComment(long announcementId, int from, int count);

	/**
	 * �û�ɾ���Լ�������
	 * 
	 * @param commentId
	 *            Ҫɾ�������۱��
	 * @param userId
	 *            ��ǰ��¼���û��û����
	 * @throws CommentDeletePermissionDeniedException
	 *             �������������û������Ǹ����۵ķ����ߣ���û��Ȩ��ɾ���������ۣ����׳����쳣
	 * @throws AnnouncementNotFoundException
	 *             ���ָ���Ĺ���û���ҵ�������׳����쳣
	 */
	public void deleteAnnouncement(long commentId, long userId) throws CommentDeletePermissionDeniedException, AnnouncementNotFoundException;

	/**
	 * ���ݹ����ź�ҳ���ѯ�ù����¶�Ӧ������������Ϣ
	 * 
	 * @param announcementId
	 *            ������
	 * @param page
	 *            ҳ��
	 * @return �������������Լ������������ݵĽ������
	 */
	public AnnouncementCommentQueryResult findCommentsByAnnouncementId(long announcementId, int page);

	/**
	 * ��һ������ɾ�������۽��лָ�����
	 * 
	 * @param commentId
	 *            Ҫ���лָ����������۱��
	 * @throws AnnouncementCommentNotFoundException
	 *             ���ָ�����۱�Ŷ�Ӧ�����۶��󲻴��ڣ����׳�����쳣
	 */
	public void recoverComment(long commentId) throws AnnouncementCommentNotFoundException;
}
