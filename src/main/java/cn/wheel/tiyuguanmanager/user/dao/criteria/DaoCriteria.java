package cn.wheel.tiyuguanmanager.user.dao.criteria;

public interface DaoCriteria {
	/**
	 * 用户名作为条件
	 */
	public static final int TYPE_USER_USERNAME = 1;
	
	/**
	 * 密码作为条件
	 */
	public static final int TYPE_USER_PASSWORD = 2;
	
	/**
	 * 角色名作为条件
	 */
	public static final int TYPE_ROLE_NAME = 3;
	
	/**
	 * 相等（等于）
	 */
	public static final int OP_EQUAL = 1;
	
	/**
	 * like 模糊匹配
	 */
	public static final int OP_LIKE = 2;
	
	/**
	 * 小于
	 */
	public static final int OP_LESS_THAN = 3;
	
	/**
	 * 大于
	 */
	public static final int OP_GREATER_THAN = 4;

	public int getType();

	public int getOp();

	public Object getContent();
}
