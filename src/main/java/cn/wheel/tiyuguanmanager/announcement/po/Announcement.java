package cn.wheel.tiyuguanmanager.announcement.po;

import java.util.Date;
import java.util.List;

import cn.wheel.tiyuguanmanager.user.po.User;

/**
 * 表示一条公告的 PO 类
 * 
 * @author DENG YURONG
 * 
 */
public class Announcement {
	private long announcementId;
	private User announcementPublisher;
	private Date announcementPublisherTime;
	private Date announcementLastChangeTime;
	private String announcementTitle;
	private String announcementContent;
	private int announcementStatus;

	private List<AnnouncementComment> announcementComments;

	public long getAnnouncementId() {
		return announcementId;
	}

	public void setAnnouncementId(long announcementId) {
		this.announcementId = announcementId;
	}

	public User getAnnouncementPublisher() {
		return announcementPublisher;
	}

	public void setAnnouncementPublisher(User announcementPublisher) {
		this.announcementPublisher = announcementPublisher;
	}

	public Date getAnnouncementPublisherTime() {
		return announcementPublisherTime;
	}

	public void setAnnouncementPublisherTime(Date announcementPublisherTime) {
		this.announcementPublisherTime = announcementPublisherTime;
	}

	public Date getAnnouncementLastChangeTime() {
		return announcementLastChangeTime;
	}

	public void setAnnouncementLastChangeTime(Date announcementLastChangeTime) {
		this.announcementLastChangeTime = announcementLastChangeTime;
	}

	public String getAnnouncementTitle() {
		return announcementTitle;
	}

	public void setAnnouncementTitle(String announcementTitle) {
		this.announcementTitle = announcementTitle;
	}

	public String getAnnouncementContent() {
		return announcementContent;
	}

	public void setAnnouncementContent(String announcementContent) {
		this.announcementContent = announcementContent;
	}

	public int getAnnouncementStatus() {
		return announcementStatus;
	}

	public void setAnnouncementStatus(int announcementStatus) {
		this.announcementStatus = announcementStatus;
	}

	public List<AnnouncementComment> getAnnouncementComments() {
		return announcementComments;
	}

	public void setAnnouncementComments(List<AnnouncementComment> announcementComments) {
		this.announcementComments = announcementComments;
	}

}
