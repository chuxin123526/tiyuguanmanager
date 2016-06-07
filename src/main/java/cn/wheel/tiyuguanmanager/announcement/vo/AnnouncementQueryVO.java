package cn.wheel.tiyuguanmanager.announcement.vo;

public class AnnouncementQueryVO {
	private int[] criteria;
	private int[] type;

	private String title;
	private String content;

	private long publisherId;

	private String beginTime;
	private String endTime;

	private String rawTime;
	private int desc;

	public int getDesc() {
		return desc;
	}

	public void setDesc(int desc) {
		this.desc = desc;
	}

	public String getRawTime() {
		return rawTime;
	}

	public void setRawTime(String rawTime) {
		this.rawTime = rawTime;
	}

	public int[] getCriteria() {
		return criteria;
	}

	public void setCriteria(int[] criteria) {
		this.criteria = criteria;
	}

	public int[] getType() {
		return type;
	}

	public void setType(int[] type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getPublisherId() {
		return publisherId;
	}

	public void setPublisherId(long publisherId) {
		this.publisherId = publisherId;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
