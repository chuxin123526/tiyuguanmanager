package cn.wheel.tiyuguanmanager.announcement.vo;

import java.util.ArrayList;
import java.util.List;

import cn.wheel.tiyuguanmanager.announcement.po.Announcement;
import cn.wheel.tiyuguanmanager.common.dao.criteria.DaoCriteria;

public class AnnouncementQueryResult {
	private long totalCount;
	private int maxPage;

	private int currentPage;
	private int currentPageItem;

	private DaoCriteria[] criterias;
	private List<Announcement> result;

	private AnnouncementQueryShowback showback;

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCurrentPageItem() {
		return currentPageItem;
	}

	public void setCurrentPageItem(int currentPageItem) {
		this.currentPageItem = currentPageItem;
	}

	public DaoCriteria[] getCriterias() {
		return criterias;
	}

	public void setCriterias(DaoCriteria[] criterias) {
		this.criterias = criterias;
	}

	public List<Announcement> getResult() {
		return result;
	}

	public void setResult(List<Announcement> result) {
		List<Announcement> retValue = new ArrayList<Announcement>();

		for (Announcement announcement : result) {
			Announcement anotherAnnouncement = new Announcement();
			anotherAnnouncement.setAnnouncementId(announcement.getAnnouncementId());
			anotherAnnouncement.setAnnouncementPublisher(announcement.getAnnouncementPublisher());
			anotherAnnouncement.setAnnouncementPublisherTime(announcement.getAnnouncementPublisherTime());
			anotherAnnouncement.setAnnouncementLastChangeTime(announcement.getAnnouncementLastChangeTime());
			anotherAnnouncement.setAnnouncementContent(announcement.getAnnouncementContent());
			anotherAnnouncement.setAnnouncementStatus(announcement.getAnnouncementStatus());

			String rawTitle = announcement.getAnnouncementTitle();
			String newTitle = "";
			if (rawTitle.length() > 30) {
				newTitle = rawTitle.substring(0, 30) + "...";
			} else {
				newTitle = rawTitle;
			}
			
			anotherAnnouncement.setAnnouncementTitle(newTitle);
			retValue.add(anotherAnnouncement);
		}

		this.result = retValue;
	}

	public AnnouncementQueryShowback getShowback() {
		return showback;
	}

	public void setShowback(AnnouncementQueryShowback showback) {
		this.showback = showback;
	}

}
