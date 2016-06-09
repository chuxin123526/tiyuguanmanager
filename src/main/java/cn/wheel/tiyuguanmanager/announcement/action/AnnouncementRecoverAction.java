package cn.wheel.tiyuguanmanager.announcement.action;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.wheel.tiyuguanmanager.announcement.constant.AnnouncementConstants;
import cn.wheel.tiyuguanmanager.announcement.exception.AnnouncementNotFoundException;
import cn.wheel.tiyuguanmanager.announcement.service.announcement.IAnnouncementService;
import cn.wheel.tiyuguanmanager.user.util.MapUtils;

@Controller("announcementRecoverAction")
@Scope("prototype")
public class AnnouncementRecoverAction {

	@Resource
	private IAnnouncementService announcementService;

	private String msgWord;

	public String getMsgWord() {
		return msgWord;
	}

	public void setMsgWord(String msgWord) {
		this.msgWord = msgWord;
	}

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
		try {
			this.announcementService.recoverAnnouncement(announcementId);
			this.ajaxReturn = MapUtils.generatorCodeMap(AnnouncementConstants.AjaxCode.ANNOUNCEMENT_RECOVER_SUCCESS);
		} catch (AnnouncementNotFoundException e) {
			this.ajaxReturn = MapUtils.generatorCodeMap(AnnouncementConstants.AjaxCode.ANNOUNCEMENT_NOT_FOUND);
		}

		return "json";
	}
}
