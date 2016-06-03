package cn.wheel.tiyuguanmanager.user.constants;

public class Constants {
	/**
	 * ��ҳ�У�ÿһҳ��Ŀ������
	 */
	public static final int ITEM_PER_PAGE = 5;

	/**
	 * ��ҳ����ǰ��ƫ��ҳ��
	 */
	public static final int NAVI_PAGE_OFFSET = 3;

	/**
	 * ���ڱ�ʾͨѶ¼�������
	 * 
	 * @author DENG YURONG
	 * 
	 */
	public static final class ContratType {
		/**
		 * �ֻ�����
		 */
		public static final int TYPE_MOBILE = 1;

		/**
		 * ͨ�ŵ�ַ
		 */
		public static final int TYPE_ADDRESS = 2;

		/**
		 * �����ʼ�
		 */
		public static final int TYPE_EMAIL = 3;
	}

	/**
	 * ֤������
	 * 
	 * @author DENG YURONG
	 * 
	 */
	public static final class IdentifierType {
		/**
		 * �������֤����
		 */
		public static final int TYPE_CITIZEN_ID = 1;

		/**
		 * ����
		 */
		public static final int TYPE_PASSPORT = 2;

		/**
		 * ����֤
		 */
		public static final int TYPE_ARMY = 3;
	}

	/**
	 * �Ա�
	 * 
	 * @author DENG YURONG
	 * 
	 */
	public static final class Gender {
		/**
		 * Ů
		 */
		public static final int GENDER_FEMALE = 0;

		/**
		 * ��
		 */
		public static final int GENDER_MALE = 1;
	}

	/**
	 * ajax ������ json �Ľ������
	 * 
	 * @author DENG YURONG
	 * 
	 */
	public static final class AjaxReturnValue {
		/**
		 * ��ǰ������Ҫ��¼
		 */
		public static final int NOT_LOGIN = 1;

		/**
		 * ��ǰ��¼�û�û��ָ����Ȩ��
		 */
		public static final int PERMISSION_DENIED = 2;

		/**
		 * ��Ϊ�û�������������µĵ�¼ʧ�ܰ�
		 */
		public static final int LOGIN_FAILED_DUE_TO_INFO_ERROR = 3;

		/**
		 * �û���Ϊ��
		 */
		public static final int LOGIN_EMPTY_USERNAME = 4;

		/**
		 * ����Ϊ��
		 */
		public static final int LOGIN_EMPTY_PASSWORD = 5;

		/**
		 * ��¼�ɹ�
		 */
		public static final int LOGIN_SUCCESS = 6;

		/**
		 * ע���ɹ�
		 */
		public static final int LOGOUT_SUCCESS = 7;

		/**
		 * ������
		 */
		public static final int FORM_EXCEPTION = 8;

		/**
		 * �û��Ѿ�����
		 */
		public static final int USER_EXIST = 9;

		/**
		 * ע��ɹ�
		 */
		public static final int REGISTER_SUCCESS = 10;

		/**
		 * ��ʾ��ǰ��¼���û����ڽ���״̬
		 */
		public static final int USER_FORBIDDEN = 11;

		/**
		 * ��ʾҪ�����������ɫ�Ѿ���ϵͳ�д���ͬ����ɫ
		 */
		public static final int ROLE_EXIST = 12;
		
		/**
		 * ��ʾ�ɹ�����һ����ɫ
		 */
		public static final int ROLE_CREATE_SUCCESS = 13;
	}

	/**
	 * ��ʾ�û�״̬
	 * 
	 * @author DENG YURONG
	 * 
	 */
	public static final class UserStatus {
		/**
		 * ����״̬
		 */
		public static final int NORMAL = 0;

		/**
		 * ��ʾ��ǰ�˺��Ѿ�������
		 */
		public static final int DISABLED = 1;
	}
}
