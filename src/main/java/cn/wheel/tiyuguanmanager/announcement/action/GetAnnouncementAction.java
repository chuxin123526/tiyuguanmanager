package cn.wheel.tiyuguanmanager.announcement.action;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.wheel.tiyuguanmanager.announcement.po.Announcement;
import cn.wheel.tiyuguanmanager.announcement.service.announcement.IAnnouncementService;

@Controller("getAnnouncementAction")
@Scope("prototype")
public class GetAnnouncementAction {

	private static SimpleDateFormat formatter;

	static {
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	@Resource
	private IAnnouncementService announcementService;

	private long id;
	private Announcement announcement;
	private String time;

	public void setId(long id) {
		this.id = id;
	}

	public Announcement getAnnouncement() {
		return announcement;
	}

	public String getTime() {
		return time;
	}

	public String execute() {
		this.announcement = this.announcementService.findAnnouncementById(id);
		if (this.announcement != null) {
			this.time = formatter.format(this.announcement.getAnnouncementPublisherTime());
		}

		return "success";
	}
}
