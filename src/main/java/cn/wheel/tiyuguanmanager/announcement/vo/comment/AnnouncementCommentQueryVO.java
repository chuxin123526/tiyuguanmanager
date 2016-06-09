package cn.wheel.tiyuguanmanager.announcement.vo.comment;

public class AnnouncementCommentQueryVO {
	public static final int CRITERIA_TYPE_COMMENT_ANNOUNCEMENT_ID = 1;

	public static final int CRITERIA_TYPE_COMMENT_PUBLISHER_ID = 2;

	public static final int CRITERIA_TYPE_COMMENT_TIME_RANGE = 3;

	public static final int CRITERIA_TYPE_COMMENT_TYPE = 4;

	public static final int CRITERIA_TYPE_COMMENT_ANNOUNCEMENT_TITLE = 5;

	private int type[];
	private int commentType[];

	private int page;
	private long announcementId;
	private long userId;
	private String rawTime;
	private String announcementTitle;

	public int[] getType() {
		return type;
	}

	public void setType(int[] type) {
		this.type = type;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getRawTime() {
		return rawTime;
	}

	public void setRawTime(String rawTime) {
		this.rawTime = rawTime;
	}

	public long getAnnouncementId() {
		return announcementId;
	}

	public void setAnnouncementId(long announcementId) {
		this.announcementId = announcementId;
	}

	public int[] getCommentType() {
		return commentType;
	}

	public void setCommentType(int[] commentType) {
		this.commentType = commentType;
	}

	public String getAnnouncementTitle() {
		return announcementTitle;
	}

	public void setAnnouncementTitle(String announcementTitle) {
		this.announcementTitle = announcementTitle;
	}

}
