package cn.wheel.tiyuguanmanager.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

@Controller("indexPageAction")
@Scope("prototype")
public class IndexPageAction {
	public String homePage() {
		return "success";
	}
}
