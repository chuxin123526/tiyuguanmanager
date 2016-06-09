package cn.wheel.tiyuguanmanager.common.dao.criteria;

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
	 * 表示使用用户编号作为查询条件
	 */
	public static final int TYPE_USER_USER_ID = 8;

	/**
	 * 根据公告评论的内容查找公告评论实体内容
	 */
	public static final int TYPE_ANNOUNCEMENT_COMMENT_CONTENT = 9;

	/**
	 * 根据公告评论发布的时间查找公告评论实体
	 */
	public static final int TYPE_ANNOUNCEMENT_COMMENT_PUBLISH_TIME = 10;

	/**
	 * 根据公告评论发布的时间段查找公告评论实体
	 */
	public static final int TYPE_ANNOUNCEMENT_COMMNET_PUBLISH_TIME_RANGE = 11;

	/**
	 * 根据公告评论者的用户编号查询公告评论实体
	 */
	public static final int TYPE_ANNOUNCEMENT_COMMENT_PUBLISHER_ID = 12;

	/**
	 * 根据公告的标题查询公告实体
	 */
	public static final int TYPE_ANNOUNCEMENT_TITLE = 13;

	/**
	 * 根据公告的发布者编号查询公告实体
	 */
	public static final int TYPE_ANNOUNCEMENT_PUBLISHER_ID = 14;

	/**
	 * 根据公告的具体发布时间查询公告实体
	 */
	public static final int TYPE_ANNOUNCEMENT_PUBLISH_TIME = 15;

	/**
	 * 根据公告发布的时间范围查询公告实体
	 */
	public static final int TYPE_ANNOUNCEMENT_PUBLISH_TIME_RANGE = 16;

	/**
	 * 根据公告的内容查询公告实体
	 */
	public static final int TYPE_ANNOUNCEMENT_CONTENT = 17;

	/**
	 * 根据公告的状态查询公告实体
	 */
	public static final int TYPE_ANNOUNCEMENT_STATUS = 18;

	/**
	 * 控制公告实体的时间排序
	 */
	public static final int TYPE_ANNOUNCEMENT_TIME_ORDER = 19;

	/**
	 * 查询多种状态的公告实体
	 */
	public static final int TYPE_ANNOUNCEMENT_MULTI_STATUS = 20;

	/**
	 * 根据公告ID查询公告的评论信息
	 */
	public static final int TYPE_ANNOUNCEMENT_COMMENT_ANNOUNCEMENT_ID = 21;

	/**
	 * 多种类型的评论查询条件
	 */
	public static final int TYPE_ANNOUNCEMENT_COMMENT_MULTI_TYPE = 22;

	/**
	 * 根据公告标题筛选评论
	 */
	public static final int TYPE_ANNOUNCEMENT_COMMENT_ANNOUNCEMENT_TITLE = 23;

	/**
	 * 根据关键字同时搜索内容和标题
	 */
	public static final int TYPE_ANNOUNCEMENT_COMMENT_SEARCH_KEYWORD = 24;

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

	/**
	 * 表示不等于
	 */
	public static final int OP_DISEQUAL = 7;

	/**
	 * 升序
	 */
	public static final int ORDER_ASC = 8;

	/**
	 * 降序
	 */
	public static final int ORDER_DESC = 9;

	public int getType();

	public int getOp();

	public Object getContent();
}
