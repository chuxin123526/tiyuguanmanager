package cn.wheel.tiyuguanmanager.user.action;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

@Controller("pageAction")
@Scope("prototype")
public class PageAction {
	public String loginPage() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (session.get("user") != null) {
			return "toIndex";
		} else {
			return "success";
		}
	}
}
