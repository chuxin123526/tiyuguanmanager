package cn.wheel.tiyuguanmanager.announcement.action.pub;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.wheel.tiyuguanmanager.announcement.po.Announcement;
import cn.wheel.tiyuguanmanager.announcement.service.announcement.IAnnouncementService;
import cn.wheel.tiyuguanmanager.announcement.vo.AnnouncementQueryResult;
import cn.wheel.tiyuguanmanager.user.util.PagingUtils;

@Controller("announcementSearch")
@Scope("prototype")
public class PublicAnnouncementSearchAction {

	@Resource
	private IAnnouncementService announcementService;

	private List<Announcement> announcements;
	private AnnouncementQueryResult result;

	private int function;

	private int page;
	private int minPage;
	private int maxPage;
	private int[] allPages;
	private String keyword;

	private String titleWord;

	public List<Announcement> getAnnouncements() {
		return announcements;
	}

	public void setAnnouncements(List<Announcement> announcements) {
		this.announcements = announcements;
	}

	public AnnouncementQueryResult getResult() {
		return result;
	}

	public void setResult(AnnouncementQueryResult result) {
		this.result = result;
	}

	public int getFunction() {
		return function;
	}

	public void setFunction(int function) {
		this.function = function;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getMinPage() {
		return minPage;
	}

	public void setMinPage(int minPage) {
		this.minPage = minPage;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	public int[] getAllPages() {
		return allPages;
	}

	public void setAllPages(int[] allPages) {
		this.allPages = allPages;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getTitleWord() {
		return titleWord;
	}

	public void setTitleWord(String titleWord) {
		this.titleWord = titleWord;
	}

	public String execute() {
		if (null == keyword || "".equals(keyword.trim())) {
			return "findAll";
		}

		this.titleWord = String.format("关键字\"%s\"的搜索结果", keyword);
		this.function = 2;
		this.result = announcementService.keywordSearch(keyword, page);
		this.announcements = this.result.getResult();

		int[] bounds = PagingUtils.getPageNavigationBounds(result.getCurrentPage(), result.getMaxPage(), 3);
		this.minPage = bounds[0];
		this.maxPage = bounds[1];
		this.allPages = PagingUtils.buildPageArray(this.minPage, this.maxPage);
		this.page = result.getCurrentPage();

		return "success";
	}
}
