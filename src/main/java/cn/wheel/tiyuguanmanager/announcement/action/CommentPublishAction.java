package cn.wheel.tiyuguanmanager.announcement.action;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.wheel.tiyuguanmanager.announcement.constant.AnnouncementConstants;
import cn.wheel.tiyuguanmanager.announcement.exception.AnnouncementNotFoundException;
import cn.wheel.tiyuguanmanager.announcement.service.announcement.comment.IAnnouncementCommentService;
import cn.wheel.tiyuguanmanager.announcement.vo.comment.AnnouncementCommentVO;
import cn.wheel.tiyuguanmanager.user.exception.UserNotExistException;
import cn.wheel.tiyuguanmanager.user.po.User;
import cn.wheel.tiyuguanmanager.user.util.MapUtils;

import com.opensymphony.xwork2.ActionContext;

@Controller("commentPublishAction")
@Scope("prototype")
public class CommentPublishAction {

	@Resource
	private IAnnouncementCommentService commentService;

	private AnnouncementCommentVO comment;
	private Map<String, Object> ajaxReturn;

	public void setComment(AnnouncementCommentVO comment) {
		this.comment = comment;
	}

	public AnnouncementCommentVO getComment() {
		return comment;
	}

	public Map<String, Object> getAjaxReturn() {
		return ajaxReturn;
	}

	public String execute() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		long userId = user.getUserId();
		comment.setPublishUserId(userId);

		try {
			this.commentService.publishComment(comment);
			this.ajaxReturn = MapUtils.generatorCodeMap(AnnouncementConstants.AjaxCode.COMMENT_PUBLISH_SUCCESS);
		} catch (AnnouncementNotFoundException e) {
			this.ajaxReturn = MapUtils.generatorCodeMap(AnnouncementConstants.AjaxCode.ANNOUNCEMENT_NOT_FOUND);
		} catch (UserNotExistException e) {
			this.ajaxReturn = MapUtils.generatorCodeMap(AnnouncementConstants.AjaxCode.COMMENT_USER_NOT_FOUND);
		}

		return "json";
	}
}
