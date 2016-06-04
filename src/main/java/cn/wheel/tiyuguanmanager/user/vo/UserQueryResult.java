package cn.wheel.tiyuguanmanager.user.vo;

import java.util.List;

import cn.wheel.tiyuguanmanager.user.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.user.po.User;

public class UserQueryResult {
	private long totalCount;
	private int maxPage;

	private int currentPage;
	private int currentPageItem;

	private DaoCriteria[] criterias;
	private List<User> result;

	private UserQueryShowback showback;

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

	public List<User> getResult() {
		return result;
	}

	public void setResult(List<User> result) {
		this.result = result;
	}

	public UserQueryShowback getShowback() {
		return showback;
	}

	public void setShowback(UserQueryShowback showback) {
		this.showback = showback;
	}

}
