package cn.wheel.tiyuguanmanager.announcement.vo;

public class AnnouncementQueryShowback {
	private boolean titleIncluded;
	private boolean contentIncluded;
	private boolean publisherIncluded;
	private boolean publishTimeIncluded;
	private boolean typeIncluded;

	private String title;
	private String content;
	private long publisherId;
	private String beginTime;
	private String endTime;

	private boolean typePublishedIncluded;
	private boolean typeDraftIncluded;
	private boolean typeDeletedIncluded;

	private String rawTime;

	private int page;

	public boolean isTitleIncluded() {
		return titleIncluded;
	}

	public void setTitleIncluded(boolean titleIncluded) {
		this.titleIncluded = titleIncluded;
	}

	public boolean isContentIncluded() {
		return contentIncluded;
	}

	public void setContentIncluded(boolean contentIncluded) {
		this.contentIncluded = contentIncluded;
	}

	public boolean isPublisherIncluded() {
		return publisherIncluded;
	}

	public void setPublisherIncluded(boolean publisherIncluded) {
		this.publisherIncluded = publisherIncluded;
	}

	public boolean isPublishTimeIncluded() {
		return publishTimeIncluded;
	}

	public void setPublishTimeIncluded(boolean publishTimeIncluded) {
		this.publishTimeIncluded = publishTimeIncluded;
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

	public boolean isTypeIncluded() {
		return typeIncluded;
	}

	public void setTypeIncluded(boolean typeIncluded) {
		this.typeIncluded = typeIncluded;
	}

	public boolean isTypePublishedIncluded() {
		return typePublishedIncluded;
	}

	public void setTypePublishedIncluded(boolean typePublishedIncluded) {
		this.typePublishedIncluded = typePublishedIncluded;
	}

	public boolean isTypeDraftIncluded() {
		return typeDraftIncluded;
	}

	public void setTypeDraftIncluded(boolean typeDraftIncluded) {
		this.typeDraftIncluded = typeDraftIncluded;
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

	public String getRawTime() {
		return rawTime;
	}

	public void setRawTime(String rawTime) {
		this.rawTime = rawTime;
	}

}
