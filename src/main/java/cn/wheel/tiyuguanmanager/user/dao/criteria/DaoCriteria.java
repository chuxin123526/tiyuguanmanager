package cn.wheel.tiyuguanmanager.user.dao.criteria;

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

	public int getType();

	public int getOp();

	public Object getContent();
}
