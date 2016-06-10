package cn.wheel.tiyuguanmanager.user.action;

import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.wheel.tiyuguanmanager.user.po.User;

import com.opensymphony.xwork2.ActionContext;

@Controller("pageAction")
@Scope("prototype")
public class PageAction {
	private User user;

	public User getUser() {
		return user;
	}

	public String loginPage() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (session.get("user") != null) {
			return "toIndex";
		} else {
			return "success";
		}
	}

	public String registerPage() {
		return "success";
	}

	public String updatePasswordPage() {
		this.user = (User) ActionContext.getContext().getSession().get("user");
		
		return "success";
	}
}
