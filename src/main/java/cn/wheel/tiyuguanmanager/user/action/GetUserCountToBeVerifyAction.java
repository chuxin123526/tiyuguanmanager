package cn.wheel.tiyuguanmanager.user.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.wheel.tiyuguanmanager.user.service.user.IUserService;

@Controller("getUserCountToBeVerify")
@Scope("prototype")
public class GetUserCountToBeVerifyAction {

	@Resource
	private IUserService userService;

	private Map<String, Object> ajaxReturn;

	public Map<String, Object> getAjaxReturn() {
		return ajaxReturn;
	}

	public void setAjaxReturn(Map<String, Object> ajaxReturn) {
		this.ajaxReturn = ajaxReturn;
	}

	public String execute() {
		this.ajaxReturn = new HashMap<String, Object>();
		int count = this.userService.countUserToBeVerified();

		this.ajaxReturn.put("count", count);

		return "json";
	}
}
