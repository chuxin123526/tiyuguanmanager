package cn.wheel.tiyuguanmanager.user.action;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.wheel.tiyuguanmanager.user.constants.Constants;
import cn.wheel.tiyuguanmanager.user.exception.FormException;
import cn.wheel.tiyuguanmanager.user.exception.UserExistException;
import cn.wheel.tiyuguanmanager.user.exception.UserForbiddenException;
import cn.wheel.tiyuguanmanager.user.po.User;
import cn.wheel.tiyuguanmanager.user.service.user.IUserService;
import cn.wheel.tiyuguanmanager.user.util.MapUtils;
import cn.wheel.tiyuguanmanager.user.util.StringUtils;
import cn.wheel.tiyuguanmanager.user.vo.UserVO;

@Controller("userAction")
@Scope("prototype")
public class UserAction {

	@Resource
	private IUserService userService;

	// json 的返回值
	private Map<String, Object> ajaxReturn;

	// 接收登录过程所需要的用户名和密码
	private String username;
	private String password;

	// 用于接收注册表单的 VO 对象
	private UserVO register;

	public Map<String, Object> getAjaxReturn() {
		return ajaxReturn;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserVO getRegister() {
		return register;
	}

	public void setRegister(UserVO register) {
		this.register = register;
	}

	public String login() {
		// 判断用户提交的表单
		if (StringUtils.isEmpty(username)) {
			this.ajaxReturn = new MapUtils().put("code", Constants.AjaxReturnValue.LOGIN_EMPTY_USERNAME).toMap();
			return "json";
		}

		if (StringUtils.isEmpty(password)) {
			this.ajaxReturn = new MapUtils().put("code", Constants.AjaxReturnValue.LOGIN_EMPTY_PASSWORD).toMap();
			return "json";
		}

		// 校验用户名和密码，以及判断用户账号当前是否处于被禁用的状态
		User user = null;
		try {
			user = userService.login(username, password);
		} catch (UserForbiddenException e) {
			this.ajaxReturn = new MapUtils().put("code", Constants.AjaxReturnValue.USER_FORBIDDEN).toMap();
			return "json";
		}

		if (user == null) {
			this.ajaxReturn = new MapUtils().put("code", Constants.AjaxReturnValue.LOGIN_FAILED_DUE_TO_INFO_ERROR).toMap();
			return "json";
		}

		// 存入 session
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put("user", user);

		this.ajaxReturn = new MapUtils().put("code", Constants.AjaxReturnValue.LOGIN_SUCCESS).toMap();
		return "json";
	}

	public String logout() {
		// 检查当前用户是否已经登录
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		if (user == null) {
			this.ajaxReturn = new MapUtils().put("code", Constants.AjaxReturnValue.NOT_LOGIN).toMap();
			return "json";
		}

		// 清空 session 中的对象
		session.remove("user");

		// 返回
		this.ajaxReturn = new MapUtils().put("code", Constants.AjaxReturnValue.LOGOUT_SUCCESS).toMap();
		return "json";
	}

	public String register() {
		try {
			userService.register(register);
			this.ajaxReturn = new MapUtils().put("code", Constants.AjaxReturnValue.REGISTER_SUCCESS).toMap();
		} catch (FormException e) {
			this.ajaxReturn = e.getErrorMessages();
			this.ajaxReturn.put("code", Constants.AjaxReturnValue.FORM_EXCEPTION);
		} catch (UserExistException e) {
			this.ajaxReturn = new MapUtils().put("code", Constants.AjaxReturnValue.USER_EXIST).toMap();
		}

		return "json";
	}

	public String logoutPage() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove("user");

		return "success";
	}
}
