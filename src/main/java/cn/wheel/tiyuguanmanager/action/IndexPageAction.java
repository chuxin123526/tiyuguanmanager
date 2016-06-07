package cn.wheel.tiyuguanmanager.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.wheel.tiyuguanmanager.announcement.constant.Constants;
import cn.wheel.tiyuguanmanager.announcement.po.Announcement;
import cn.wheel.tiyuguanmanager.announcement.service.announcement.IAnnouncementService;
import cn.wheel.tiyuguanmanager.announcement.vo.AnnouncementQueryVO;

@Controller("indexPageAction")
@Scope("prototype")
public class IndexPageAction {
	@Resource
	private IAnnouncementService announcementService;

	private List<Announcement> announcementList;

	public List<Announcement> getAnnouncementList() {
		return announcementList;
	}

	public void setAnnouncementList(List<Announcement> announcementList) {
		this.announcementList = announcementList;
	}

	public String homePage() {
		AnnouncementQueryVO queryVO = new AnnouncementQueryVO();
		queryVO.setCriteria(new int[] { 5 });
		queryVO.setType(new int[] { Constants.AnnouncementStatus.STATUS_PUBLISHED_ANNOUNCEMENT });
		queryVO.setDesc(1);
		
		this.announcementList = this.announcementService.queryAnnouncement(queryVO, 1).getResult();

		return "success";
	}
}
