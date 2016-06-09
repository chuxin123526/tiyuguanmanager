package cn.wheel.tiyuguanmanager.announcement.vo.comment;

import java.util.List;

import cn.wheel.tiyuguanmanager.announcement.po.AnnouncementComment;

public class AnnouncementCommentQueryResult {
	private long totalCount;
	private int maxPage;

	private int currentPage;
	private int currentPageItem;

	private List<AnnouncementComment> result;
	private AnnouncementCommentQueryShowback showback;

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int page) {
		this.maxPage = page;
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

	public List<AnnouncementComment> getResult() {
		return result;
	}

	public void setResult(List<AnnouncementComment> result) {
		this.result = result;
	}

	public AnnouncementCommentQueryShowback getShowback() {
		return showback;
	}

	public void setShowback(AnnouncementCommentQueryShowback showback) {
		this.showback = showback;
	}

}
