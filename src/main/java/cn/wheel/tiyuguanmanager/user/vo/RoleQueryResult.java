package cn.wheel.tiyuguanmanager.user.vo;

import java.util.List;

import cn.wheel.tiyuguanmanager.user.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.user.po.Role;

public class RoleQueryResult {
	private long totalCount;
	private int maxPage;

	private int currentPage;
	private int curragePageItem;

	private DaoCriteria[] criterias;
	private List<Role> result;

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

	public int getCurragePageItem() {
		return curragePageItem;
	}

	public void setCurragePageItem(int curragePageItem) {
		this.curragePageItem = curragePageItem;
	}

	public DaoCriteria[] getCriterias() {
		return criterias;
	}

	public void setCriterias(DaoCriteria[] criterias) {
		this.criterias = criterias;
	}

	public List<Role> getResult() {
		return result;
	}

	public void setResult(List<Role> result) {
		this.result = result;
	}

}
