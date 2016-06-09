package cn.wheel.tiyuguanmanager.announcement.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.wheel.tiyuguanmanager.announcement.po.Announcement;
import cn.wheel.tiyuguanmanager.announcement.service.announcement.IAnnouncementService;
import cn.wheel.tiyuguanmanager.announcement.vo.AnnouncementQueryResult;
import cn.wheel.tiyuguanmanager.announcement.vo.AnnouncementQueryShowback;
import cn.wheel.tiyuguanmanager.announcement.vo.AnnouncementQueryVO;
import cn.wheel.tiyuguanmanager.user.util.PagingUtils;

@Controller("announcementTrash")
@Scope("prototype")
public class AnnouncementTrashAction {

	@Resource
	private IAnnouncementService announcementService;

	private int function;
	private int page;
	private String msgWord;

	private int minPage;
	private int maxPage;
	private int[] allPages;

	private List<Announcement> announcementList;
	private AnnouncementQueryShowback showback;

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

	public String getMsgWord() {
		return msgWord;
	}

	public void setMsgWord(String msgWord) {
		this.msgWord = msgWord;
	}

	public List<Announcement> getAnnouncementList() {
		return announcementList;
	}

	public void setAnnouncementList(List<Announcement> announcementList) {
		this.announcementList = announcementList;
	}

	public AnnouncementQueryShowback getShowback() {
		return showback;
	}

	public void setShowback(AnnouncementQueryShowback showback) {
		this.showback = showback;
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

	public String execute() {
		this.function = 4;

		AnnouncementQueryVO query = new AnnouncementQueryVO();
		query.setCriteria(new int[] { 5 });
		query.setType(new int[] { 4 });

		AnnouncementQueryResult result = this.announcementService.queryAnnouncement(query, this.page);

		int[] bounds = PagingUtils.getPageNavigationBounds(result.getCurrentPage(), result.getMaxPage(), 3);
		this.minPage = bounds[0];
		this.maxPage = bounds[1];
		this.allPages = PagingUtils.buildPageArray(minPage, maxPage);
		this.page = result.getCurrentPage();

		this.announcementList = result.getResult();

		return "success";
	}
}
