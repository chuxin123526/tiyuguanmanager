package cn.wheel.tiyuguanmanager.user.action;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.wheel.tiyuguanmanager.user.constants.UserConstants;
import cn.wheel.tiyuguanmanager.user.exception.UserAuthException;
import cn.wheel.tiyuguanmanager.user.exception.UserNotExistException;
import cn.wheel.tiyuguanmanager.user.po.User;
import cn.wheel.tiyuguanmanager.user.service.user.IUserService;
import cn.wheel.tiyuguanmanager.user.util.MapUtils;

@Controller("passwordUpdate")
@Scope("prototype")
public class PasswordUpdateAction {

	@Resource
	private IUserService userService;

	private String oldPwd;
	private String newPwd;

	private Map<String, Object> ajaxReturn;

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public Map<String, Object> getAjaxReturn() {
		return ajaxReturn;
	}

	public void setAjaxReturn(Map<String, Object> ajaxReturn) {
		this.ajaxReturn = ajaxReturn;
	}

	public String execute() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		if (user == null) {
			// 1
			this.ajaxReturn = MapUtils.generatorCodeMap(UserConstants.AjaxReturnValue.NOT_LOGIN);
			return "json";
		}

		try {
			// 26
			this.userService.updatePassword(user.getUserId(), oldPwd, newPwd);
			this.ajaxReturn = MapUtils.generatorCodeMap(UserConstants.AjaxReturnValue.USER_UPDATE_PASSWORD_SUCCESS);
		} catch (UserAuthException e) {
			// 27
			this.ajaxReturn = MapUtils.generatorCodeMap(UserConstants.AjaxReturnValue.USER_UPDATE_PASSWORD_AUTH_FAILED);
		} catch (UserNotExistException e) {
			// 20
			this.ajaxReturn = MapUtils.generatorCodeMap(UserConstants.AjaxReturnValue.USER_NOT_EXIST);
		}

		return "json";
	}
}
