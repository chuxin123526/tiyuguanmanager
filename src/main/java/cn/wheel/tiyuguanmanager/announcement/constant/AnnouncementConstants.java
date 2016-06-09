package cn.wheel.tiyuguanmanager.announcement.constant;

public class AnnouncementConstants {
	/**
	 * 用于保存公告的状态
	 * 
	 * @author DENG YURONG
	 * 
	 */
	public static final class AnnouncementStatus {
		/**
		 * 表示新的公告
		 */
		public static final int STATUS_NEW_ANNOUNCEMENT = 1;

		/**
		 * 表示这是草稿公告
		 */
		public static final int STATUS_DRAFT_ANNOUNCEMENT = 2;

		/**
		 * 表示这是已经发布的公告
		 */
		public static final int STATUS_PUBLISHED_ANNOUNCEMENT = 3;

		/**
		 * 表示这个已经是被删除的公告
		 */
		public static final int STATUS_DELETED_ANNOUNCEMENT = 4;
	}

	/**
	 * 表示公告评论的状态
	 * 
	 * @author DENG YURONG
	 * 
	 */
	public static final class CommentStatus {
		/**
		 * 表示已经发布的评论
		 */
		public static final int STATUS_PUBLISHED = 1;

		/**
		 * 表示被隐藏起来的评论
		 */
		public static final int STATUS_HIDDEN = 2;

		/**
		 * 由用户自己删除的评论
		 */
		public static final int STATUS_DELETED = 3;
	}

	/**
	 * 储存 ajax 返回常量
	 * 
	 * @author DENG YURONG
	 * 
	 */
	public static final class AjaxCode {
		/**
		 * 表示表单校验失败
		 */
		public static final int FORM_EXCEPTION = 1;

		/**
		 * 表示当前没有进行该操作的权限
		 */
		public static final int PERMISSION_DENIED = 2;

		/**
		 * 表示成功发布公告
		 */
		public static final int ANNOUNCEMENT_PUBLISH_SUCCESS = 3;

		/**
		 * 表单传来的用户信息无效
		 */
		public static final int ANNOUNCEMENT_PUBLISHER_NOT_FOUND = 4;

		/**
		 * 表示成功保存到草稿
		 */
		public static final int ANNOUNCEMENT_PUBLISH_DRAFT_SUCCESS = 5;

		/**
		 * 表示成功修改草稿内容
		 */
		public static final int ANNOUNCEMENT_DRAFT_UPDATE_SUCCESS = 6;

		/**
		 * 指定编号的公告的类型不是草稿类型
		 */
		public static final int ANNOUNCEMENT_SPECIFIED_ANNOUNCEMENT_IS_NOT_DRAFT = 7;

		/**
		 * 成功修改公告内容
		 */
		public static final int ANNOUNCEMENT_UPDATE_SUCCESS = 8;

		/**
		 * 未找到指定编号的公告对象
		 */
		public static final int ANNOUNCEMENT_NOT_FOUND = 9;

		/**
		 * 成功将草稿发布为正式公告
		 */
		public static final int ANNOUNCEMENT_PUSH_DRAFT_SUCCESS = 10;

		/**
		 * 成功删除指定的公告
		 */
		public static final int ANNOUNCEMENT_DELETE_SUCCESS = 11;

		/**
		 * 成功发布公告评论
		 */
		public static final int COMMENT_PUBLISH_SUCCESS = 12;

		/**
		 * 指定的用户编号无效
		 */
		public static final int COMMENT_USER_NOT_FOUND = 13;

		/**
		 * 表示成功删除一条评论
		 */
		public static final int COMMENT_DELETE_SUCCESS = 14;

		/**
		 * 用户尝试删除一条不是自己发布的评论时会抛出这个异常
		 */
		public static final int COMMENT_DELETE_DENIED = 15;

		/**
		 * 成功隐藏一条评论
		 */
		public static final int COMMENT_HIDE_SUCCESS = 16;

		/**
		 * 成功恢复一条评论
		 */
		public static final int COMMENT_RECOVER_SUCCESS = 17;

		/**
		 * 没有找到指定的评论
		 */
		public static final int COMMENT_NOT_FOUND = 18;
	}
}
