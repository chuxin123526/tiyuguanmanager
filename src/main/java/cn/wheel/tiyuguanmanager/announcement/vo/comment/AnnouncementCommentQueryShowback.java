package cn.wheel.tiyuguanmanager.announcement.vo.comment;

public class AnnouncementCommentQueryShowback {
	private boolean announcementIdIncluded;
	private boolean commentPublisherIdIncluded;
	private boolean commentTimeRangeIncluded;
	private boolean commentTypeIncluded;
	private boolean announcementTitleIncluded;

	private boolean typePublishedIncluded;
	private boolean typeHiddenIncluded;
	private boolean typeDeletedIncluded;

	private int page;
	private long announcementId;
	private long userId;
	private String rawTime;
	private String annonucementTitle;

	public boolean isAnnouncementIdIncluded() {
		return announcementIdIncluded;
	}

	public void setAnnouncementIdIncluded(boolean announcementIdIncluded) {
		this.announcementIdIncluded = announcementIdIncluded;
	}

	public boolean isCommentPublisherIdIncluded() {
		return commentPublisherIdIncluded;
	}

	public void setCommentPublisherIdIncluded(boolean commentPublisherIdIncluded) {
		this.commentPublisherIdIncluded = commentPublisherIdIncluded;
	}

	public boolean isCommentTimeRangeIncluded() {
		return commentTimeRangeIncluded;
	}

	public void setCommentTimeRangeIncluded(boolean commentTimeRangeIncluded) {
		this.commentTimeRangeIncluded = commentTimeRangeIncluded;
	}

	public boolean isCommentTypeIncluded() {
		return commentTypeIncluded;
	}

	public void setCommentTypeIncluded(boolean commentTypeIncluded) {
		this.commentTypeIncluded = commentTypeIncluded;
	}

	public boolean isAnnouncementTitleIncluded() {
		return announcementTitleIncluded;
	}

	public void setAnnouncementTitleIncluded(boolean announcementTitleIncluded) {
		this.announcementTitleIncluded = announcementTitleIncluded;
	}

	public boolean isTypePublishedIncluded() {
		return typePublishedIncluded;
	}

	public void setTypePublishedIncluded(boolean typePublishedIncluded) {
		this.typePublishedIncluded = typePublishedIncluded;
	}

	public boolean isTypeHiddenIncluded() {
		return typeHiddenIncluded;
	}

	public void setTypeHiddenIncluded(boolean typeHiddenIncluded) {
		this.typeHiddenIncluded = typeHiddenIncluded;
	}

	public boolean isTypeDeletedIncluded() {
		return typeDeletedIncluded;
	}

	public void setTypeDeletedIncluded(boolean typeDeletedIncluded) {
		this.typeDeletedIncluded = typeDeletedIncluded;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public long getAnnouncementId() {
		return announcementId;
	}

	public void setAnnouncementId(long announcementId) {
		this.announcementId = announcementId;
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

	public String getAnnonucementTitle() {
		return annonucementTitle;
	}

	public void setAnnonucementTitle(String annonucementTitle) {
		this.annonucementTitle = annonucementTitle;
	}

}
