package cn.wheel.tiyuguanmanager.announcement.constant;

public class Constants {
	/**
	 * ���ڱ��湫���״̬
	 * 
	 * @author DENG YURONG
	 * 
	 */
	public static final class AnnouncementStatus {
		/**
		 * ��ʾ�µĹ���
		 */
		public static final int STATUS_NEW_ANNOUNCEMENT = 1;

		/**
		 * ��ʾ���ǲݸ幫��
		 */
		public static final int STATUS_DRAFT_ANNOUNCEMENT = 2;

		/**
		 * ��ʾ�����Ѿ������Ĺ���
		 */
		public static final int STATUS_PUBLISHED_ANNOUNCEMENT = 3;

		/**
		 * ��ʾ����Ѿ��Ǳ�ɾ���Ĺ���
		 */
		public static final int STATUS_DELETED_ANNOUNCEMENT = 4;
	}

	/**
	 * ��ʾ�������۵�״̬
	 * 
	 * @author DENG YURONG
	 * 
	 */
	public static final class CommentStatus {
		/**
		 * ��ʾ�Ѿ�����������
		 */
		public static final int STATUS_PUBLISHED = 1;

		/**
		 * ��ʾ����������������
		 */
		public static final int STATUS_HIDDEN = 2;
	}
}
