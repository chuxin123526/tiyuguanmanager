package cn.wheel.tiyuguanmanager.announcement.action;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.wheel.tiyuguanmanager.announcement.constant.AnnouncementConstants;
import cn.wheel.tiyuguanmanager.announcement.exception.AnnouncementNotFoundException;
import cn.wheel.tiyuguanmanager.announcement.exception.CommentDeletePermissionDeniedException;
import cn.wheel.tiyuguanmanager.announcement.service.announcement.comment.IAnnouncementCommentService;
import cn.wheel.tiyuguanmanager.user.po.User;
import cn.wheel.tiyuguanmanager.user.util.MapUtils;

@Controller("deleteAnnouncementComment")
@Scope("prototype")
public class DeleteAnnouncementCommentUserAction {

	@Resource
	private IAnnouncementCommentService service;

	private long commentId;
	private Map<String, Object> ajaxReturn;

	public long getCommentId() {
		return commentId;
	}

	public void setCommentId(long commentId) {
		this.commentId = commentId;
	}

	public Map<String, Object> getAjaxReturn() {
		return ajaxReturn;
	}

	public void setAjaxReturn(Map<String, Object> ajaxReturn) {
		this.ajaxReturn = ajaxReturn;
	}

	public String execute() {
		User user = (User) ActionContext.getContext().getSession().get("user");
		long userId = user.getUserId();

		try {
			this.service.deleteAnnouncement(commentId, userId);
			this.ajaxReturn = MapUtils.generatorCodeMap(AnnouncementConstants.AjaxCode.COMMENT_DELETE_SUCCESS);
		} catch (AnnouncementNotFoundException e) {
			this.ajaxReturn = MapUtils.generatorCodeMap(AnnouncementConstants.AjaxCode.ANNOUNCEMENT_NOT_FOUND);
		} catch (CommentDeletePermissionDeniedException e) {
			this.ajaxReturn = MapUtils.generatorCodeMap(AnnouncementConstants.AjaxCode.COMMENT_DELETE_DENIED);
		}

		return "json";
	}
}
