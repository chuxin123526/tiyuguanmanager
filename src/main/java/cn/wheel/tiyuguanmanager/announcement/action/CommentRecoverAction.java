package cn.wheel.tiyuguanmanager.announcement.action;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.wheel.tiyuguanmanager.announcement.constant.AnnouncementConstants;
import cn.wheel.tiyuguanmanager.announcement.exception.AnnouncementCommentNotFoundException;
import cn.wheel.tiyuguanmanager.announcement.service.announcement.comment.IAnnouncementCommentService;
import cn.wheel.tiyuguanmanager.user.util.MapUtils;

@Controller("commentRecover")
@Scope("prototype")
public class CommentRecoverAction {

	@Resource
	private IAnnouncementCommentService commentService;

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
		try {
			this.commentService.recoverComment(commentId);
			this.ajaxReturn = MapUtils.generatorCodeMap(AnnouncementConstants.AjaxCode.COMMENT_RECOVER_SUCCESS);
		} catch (AnnouncementCommentNotFoundException e) {
			this.ajaxReturn = MapUtils.generatorCodeMap(AnnouncementConstants.AjaxCode.COMMENT_NOT_FOUND);
		}

		return "json";
	}
}
