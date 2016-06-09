package cn.wheel.tiyuguanmanager.common.dao.criteria;

public interface DaoCriteria {
	/**
	 * �û�����Ϊ������ѯ�û�ʵ��
	 */
	public static final int TYPE_USER_USERNAME = 1;

	/**
	 * ������Ϊ������ѯ�û�ʵ��
	 */
	public static final int TYPE_USER_PASSWORD = 2;

	/**
	 * ��ɫ����Ϊ������ѯ��ɫʵ��
	 */
	public static final int TYPE_ROLE_NAME = 3;

	/**
	 * ʹ���û������Ľ�ɫ���Ʋ�ѯ�û�ʵ�����
	 */
	public static final int TYPE_USER_ROLE_NAME = 4;

	/**
	 * ʹ���û������Ľ�ɫ��Ų�ѯ�û�ʵ�����
	 */
	public static final int TYPE_USER_ROLE_ID = 5;

	/**
	 * ʹ���û�������Ϊ��ѯ����
	 */
	public static final int TYPE_USER_ACCOUNT_TYPE = 6;

	/**
	 * ��ʾ�ڲ�ѯ�û��˺ŵĹ������Ƿ��ų������õ��˻�
	 */
	public static final int TYPE_USER_EXCLUED_FORBIDDEN = 7;

	/**
	 * ��ʾʹ���û������Ϊ��ѯ����
	 */
	public static final int TYPE_USER_USER_ID = 8;

	/**
	 * ���ݹ������۵����ݲ��ҹ�������ʵ������
	 */
	public static final int TYPE_ANNOUNCEMENT_COMMENT_CONTENT = 9;

	/**
	 * ���ݹ������۷�����ʱ����ҹ�������ʵ��
	 */
	public static final int TYPE_ANNOUNCEMENT_COMMENT_PUBLISH_TIME = 10;

	/**
	 * ���ݹ������۷�����ʱ��β��ҹ�������ʵ��
	 */
	public static final int TYPE_ANNOUNCEMENT_COMMNET_PUBLISH_TIME_RANGE = 11;

	/**
	 * ���ݹ��������ߵ��û���Ų�ѯ��������ʵ��
	 */
	public static final int TYPE_ANNOUNCEMENT_COMMENT_PUBLISHER_ID = 12;

	/**
	 * ���ݹ���ı����ѯ����ʵ��
	 */
	public static final int TYPE_ANNOUNCEMENT_TITLE = 13;

	/**
	 * ���ݹ���ķ����߱�Ų�ѯ����ʵ��
	 */
	public static final int TYPE_ANNOUNCEMENT_PUBLISHER_ID = 14;

	/**
	 * ���ݹ���ľ��巢��ʱ���ѯ����ʵ��
	 */
	public static final int TYPE_ANNOUNCEMENT_PUBLISH_TIME = 15;

	/**
	 * ���ݹ��淢����ʱ�䷶Χ��ѯ����ʵ��
	 */
	public static final int TYPE_ANNOUNCEMENT_PUBLISH_TIME_RANGE = 16;

	/**
	 * ���ݹ�������ݲ�ѯ����ʵ��
	 */
	public static final int TYPE_ANNOUNCEMENT_CONTENT = 17;

	/**
	 * ���ݹ����״̬��ѯ����ʵ��
	 */
	public static final int TYPE_ANNOUNCEMENT_STATUS = 18;

	/**
	 * ���ƹ���ʵ���ʱ������
	 */
	public static final int TYPE_ANNOUNCEMENT_TIME_ORDER = 19;

	/**
	 * ��ѯ����״̬�Ĺ���ʵ��
	 */
	public static final int TYPE_ANNOUNCEMENT_MULTI_STATUS = 20;

	/**
	 * ���ݹ���ID��ѯ�����������Ϣ
	 */
	public static final int TYPE_ANNOUNCEMENT_COMMENT_ANNOUNCEMENT_ID = 21;

	/**
	 * �������͵����۲�ѯ����
	 */
	public static final int TYPE_ANNOUNCEMENT_COMMENT_MULTI_TYPE = 22;

	/**
	 * ���ݹ������ɸѡ����
	 */
	public static final int TYPE_ANNOUNCEMENT_COMMENT_ANNOUNCEMENT_TITLE = 23;

	/**
	 * ���ݹؼ���ͬʱ�������ݺͱ���
	 */
	public static final int TYPE_ANNOUNCEMENT_COMMENT_SEARCH_KEYWORD = 24;

	/**
	 * ��ȣ����ڣ�
	 */
	public static final int OP_EQUAL = 1;

	/**
	 * like ģ��ƥ��
	 */
	public static final int OP_LIKE = 2;

	/**
	 * С��
	 */
	public static final int OP_LESS_THAN = 3;

	/**
	 * ����
	 */
	public static final int OP_GREATER_THAN = 4;

	/**
	 * ��ʾ�߼���
	 */
	public static final int OP_TRUE = 5;

	/**
	 * ��ʾ�߼���
	 */
	public static final int OP_FALSE = 6;

	/**
	 * ��ʾ������
	 */
	public static final int OP_DISEQUAL = 7;

	/**
	 * ����
	 */
	public static final int ORDER_ASC = 8;

	/**
	 * ����
	 */
	public static final int ORDER_DESC = 9;

	public int getType();

	public int getOp();

	public Object getContent();
}
