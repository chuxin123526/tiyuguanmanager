package cn.wheel.tiyuguanmanager.announcement.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("announcementCommentQueryPage")
@Scope("prototype")
public class AnnouncementCommentQueryPageAction {

	private static SimpleDateFormat formatter;

	static {
		formatter = new SimpleDateFormat("yyyy-MM-dd");
	}

	private String rawTime;

	public String getRawTime() {
		return rawTime;
	}

	public String execute() {
		String dateValue = formatter.format(new Date());
		this.rawTime = String.format("%s - %s", dateValue, dateValue);

		return "success";
	}
}
