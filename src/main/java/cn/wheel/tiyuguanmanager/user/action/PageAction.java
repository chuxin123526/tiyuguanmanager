package cn.wheel.tiyuguanmanager.user.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("pageAction")
@Scope("prototype")
public class PageAction {
	public String execute() {
		return "success";
	}
}
