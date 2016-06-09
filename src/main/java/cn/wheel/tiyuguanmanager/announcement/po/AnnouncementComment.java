package cn.wheel.tiyuguanmanager.announcement.po;

import java.util.Date;

import cn.wheel.tiyuguanmanager.user.po.User;

public class AnnouncementComment {
	private long commentId;
	private User commentPublisher;
	private Date commentPublishTime;
	private String commentContent;
	private int commentStatus;
	private Announcement announcement;

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public User getCommentPublisher() {
		return commentPublisher;
	}

	public void setCommentPublisher(User commentPublisher) {
		this.commentPublisher = commentPublisher;
	}

	public Date getCommentPublishTime() {
		return commentPublishTime;
	}

	public void setCommentPublishTime(Date commentPublishTime) {
		this.commentPublishTime = commentPublishTime;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public int getCommentStatus() {
		return commentStatus;
	}

	public void setCommentStatus(int commentStatus) {
		this.commentStatus = commentStatus;
	}

	public Announcement getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}

}
