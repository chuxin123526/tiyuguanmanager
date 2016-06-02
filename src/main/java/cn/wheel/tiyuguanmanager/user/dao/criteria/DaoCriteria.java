package cn.wheel.tiyuguanmanager.user.dao.criteria;

public interface DaoCriteria {
	/**
	 * �û�����Ϊ����
	 */
	public static final int TYPE_USER_USERNAME = 1;
	
	/**
	 * ������Ϊ����
	 */
	public static final int TYPE_USER_PASSWORD = 2;
	
	/**
	 * ��ɫ����Ϊ����
	 */
	public static final int TYPE_ROLE_NAME = 3;
	
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

	public int getType();

	public int getOp();

	public Object getContent();
}
