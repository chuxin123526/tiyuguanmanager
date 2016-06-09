package cn.wheel.tiyuguanmanager.announcement.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.wheel.tiyuguanmanager.announcement.po.AnnouncementComment;
import cn.wheel.tiyuguanmanager.announcement.service.announcement.comment.IAnnouncementCommentService;
import cn.wheel.tiyuguanmanager.announcement.vo.comment.AnnouncementCommentQueryResult;
import cn.wheel.tiyuguanmanager.announcement.vo.comment.AnnouncementCommentQueryShowback;
import cn.wheel.tiyuguanmanager.announcement.vo.comment.AnnouncementCommentQueryVO;
import cn.wheel.tiyuguanmanager.user.util.PagingUtils;

@Controller("doCommentQuery")
@Scope("prototype")
public class CommentQueryAction {

	@Resource
	private IAnnouncementCommentService commentService;

	private AnnouncementCommentQueryVO query;
	private String titleWord;
	private int function;
	private int page;

	private int minPage;
	private int maxPage;
	private int[] allPages;

	private AnnouncementCommentQueryResult result;
	private List<AnnouncementComment> comments;
	private AnnouncementCommentQueryShowback showback;

	public AnnouncementCommentQueryVO getQuery() {
		return query;
	}

	public void setQuery(AnnouncementCommentQueryVO query) {
		this.query = query;
	}

	public String getTitleWord() {
		return titleWord;
	}

	public void setTitleWord(String titleWord) {
		this.titleWord = titleWord;
	}

	public int getFunction() {
		return function;
	}

	public void setFunction(int function) {
		this.function = function;
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

	public AnnouncementCommentQueryResult getResult() {
		return result;
	}

	public void setResult(AnnouncementCommentQueryResult result) {
		this.result = result;
	}

	public List<AnnouncementComment> getComments() {
		return comments;
	}

	public void setComments(List<AnnouncementComment> comments) {
		this.comments = comments;
	}

	public AnnouncementCommentQueryShowback getShowback() {
		return showback;
	}

	public void setShowback(AnnouncementCommentQueryShowback showback) {
		this.showback = showback;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String execute() {
		this.function = 1;
		this.titleWord = "²éÑ¯½á¹û";

		this.result = this.commentService.query(query);
		this.comments = this.result.getResult();
		this.showback = this.result.getShowback();

		int[] bounds = PagingUtils.getPageNavigationBounds(result.getCurrentPage(), result.getMaxPage(), 3);
		this.minPage = bounds[0];
		this.maxPage = bounds[1];
		this.allPages = PagingUtils.buildPageArray(minPage, maxPage);
		this.page = result.getCurrentPage();

		return "success";
	}
}
