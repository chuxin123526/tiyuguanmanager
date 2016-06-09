package cn.wheel.tiyuguanmanager.announcement.vo.comment;

public class AnnouncementCommentVO {
	private long announcementId;
	private String content;
	private long publishUserId;

	public long getAnnouncementId() {
		return announcementId;
	}

	public void setAnnouncementId(long announcementId) {
		this.announcementId = announcementId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getPublishUserId() {
		return publishUserId;
	}

	public void setPublishUserId(long publishUserId) {
		this.publishUserId = publishUserId;
	}

}
