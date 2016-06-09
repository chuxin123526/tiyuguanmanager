package cn.wheel.tiyuguanmanager.announcement.action;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.wheel.tiyuguanmanager.announcement.service.announcement.comment.IAnnouncementCommentService;
import cn.wheel.tiyuguanmanager.user.util.MapUtils;

@Controller("getAnnouncementCommentCount")
@Scope("prototype")
public class GetAnnouncementCommentCountAction {

	@Resource
	private IAnnouncementCommentService commentService;

	private long announcementId;
	private Map<String, Object> ajaxReturn;

	public long getAnnouncementId() {
		return announcementId;
	}

	public void setAnnouncementId(long announcementId) {
		this.announcementId = announcementId;
	}

	public Map<String, Object> getAjaxReturn() {
		return ajaxReturn;
	}

	public void setAjaxReturn(Map<String, Object> ajaxReturn) {
		this.ajaxReturn = ajaxReturn;
	}

	public String execute() {
		this.ajaxReturn = MapUtils.generatorCodeMap(0);
		this.ajaxReturn.put("count", commentService.getCommentCountOfAnnouncement(announcementId));

		return "json";
	}
}
