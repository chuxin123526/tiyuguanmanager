package cn.wheel.tiyuguanmanager.announcement.constant;

public class AnnouncementConstants {
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

		/**
		 * ���û��Լ�ɾ��������
		 */
		public static final int STATUS_DELETED = 3;
	}

	/**
	 * ���� ajax ���س���
	 * 
	 * @author DENG YURONG
	 * 
	 */
	public static final class AjaxCode {
		/**
		 * ��ʾ��У��ʧ��
		 */
		public static final int FORM_EXCEPTION = 1;

		/**
		 * ��ʾ��ǰû�н��иò�����Ȩ��
		 */
		public static final int PERMISSION_DENIED = 2;

		/**
		 * ��ʾ�ɹ���������
		 */
		public static final int ANNOUNCEMENT_PUBLISH_SUCCESS = 3;

		/**
		 * ���������û���Ϣ��Ч
		 */
		public static final int ANNOUNCEMENT_PUBLISHER_NOT_FOUND = 4;

		/**
		 * ��ʾ�ɹ����浽�ݸ�
		 */
		public static final int ANNOUNCEMENT_PUBLISH_DRAFT_SUCCESS = 5;

		/**
		 * ��ʾ�ɹ��޸Ĳݸ�����
		 */
		public static final int ANNOUNCEMENT_DRAFT_UPDATE_SUCCESS = 6;

		/**
		 * ָ����ŵĹ�������Ͳ��ǲݸ�����
		 */
		public static final int ANNOUNCEMENT_SPECIFIED_ANNOUNCEMENT_IS_NOT_DRAFT = 7;

		/**
		 * �ɹ��޸Ĺ�������
		 */
		public static final int ANNOUNCEMENT_UPDATE_SUCCESS = 8;

		/**
		 * δ�ҵ�ָ����ŵĹ������
		 */
		public static final int ANNOUNCEMENT_NOT_FOUND = 9;

		/**
		 * �ɹ����ݸ巢��Ϊ��ʽ����
		 */
		public static final int ANNOUNCEMENT_PUSH_DRAFT_SUCCESS = 10;

		/**
		 * �ɹ�ɾ��ָ���Ĺ���
		 */
		public static final int ANNOUNCEMENT_DELETE_SUCCESS = 11;

		/**
		 * �ɹ�������������
		 */
		public static final int COMMENT_PUBLISH_SUCCESS = 12;

		/**
		 * ָ�����û������Ч
		 */
		public static final int COMMENT_USER_NOT_FOUND = 13;

		/**
		 * ��ʾ�ɹ�ɾ��һ������
		 */
		public static final int COMMENT_DELETE_SUCCESS = 14;

		/**
		 * �û�����ɾ��һ�������Լ�����������ʱ���׳�����쳣
		 */
		public static final int COMMENT_DELETE_DENIED = 15;

		/**
		 * �ɹ�����һ������
		 */
		public static final int COMMENT_HIDE_SUCCESS = 16;

		/**
		 * �ɹ��ָ�һ������
		 */
		public static final int COMMENT_RECOVER_SUCCESS = 17;

		/**
		 * û���ҵ�ָ��������
		 */
		public static final int COMMENT_NOT_FOUND = 18;
	}
}
