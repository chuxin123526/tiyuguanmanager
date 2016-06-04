package cn.wheel.tiyuguanmanager.user.dao.criteria;

public interface DaoCriteria {
	/**
	 * 用户名作为条件查询用户实体
	 */
	public static final int TYPE_USER_USERNAME = 1;

	/**
	 * 密码作为条件查询用户实体
	 */
	public static final int TYPE_USER_PASSWORD = 2;

	/**
	 * 角色名作为条件查询角色实体
	 */
	public static final int TYPE_ROLE_NAME = 3;
	
	/**
	 * 使用用户所属的角色名称查询用户实体对象
	 */
	public static final int TYPE_USER_ROLE_NAME = 4;
	
	/**
	 * 使用用户所属的角色编号查询用户实体对象
	 */
	public static final int TYPE_USER_ROLE_ID = 5;
	
	/**
	 * 使用用户类型作为查询条件
	 */
	public static final int TYPE_USER_ACCOUNT_TYPE = 6;
	
	/**
	 * 表示在查询用户账号的过程中是否排除被禁用的账户
	 */
	public static final int TYPE_USER_EXCLUED_FORBIDDEN = 7;

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
	
	/**
	 * 表示逻辑真
	 */
	public static final int OP_TRUE = 5;
	
	/**
	 * 表示逻辑假
	 */
	public static final int OP_FALSE = 6;

	public int getType();

	public int getOp();

	public Object getContent();
}
