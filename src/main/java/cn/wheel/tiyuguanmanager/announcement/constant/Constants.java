package cn.wheel.tiyuguanmanager.announcement.constant;

public class Constants {
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
	}
}
