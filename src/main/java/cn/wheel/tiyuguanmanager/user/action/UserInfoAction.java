package cn.wheel.tiyuguanmanager.user.action;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.wheel.tiyuguanmanager.user.po.User;
import cn.wheel.tiyuguanmanager.user.service.user.IUserService;

@Controller("userInfo")
@Scope("prototype")
public class UserInfoAction {

	@Resource
	private IUserService userService;

	private long userId;
	private User user;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String execute() {
		this.user = this.userService.findUserById(userId);

		return "success";
	}
}
