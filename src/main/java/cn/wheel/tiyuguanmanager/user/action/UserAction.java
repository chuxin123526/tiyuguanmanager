package cn.wheel.tiyuguanmanager.user.action;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.wheel.tiyuguanmanager.user.constants.Constants;
import cn.wheel.tiyuguanmanager.user.po.User;
import cn.wheel.tiyuguanmanager.user.service.user.IUserService;
import cn.wheel.tiyuguanmanager.user.util.MapUtils;
import cn.wheel.tiyuguanmanager.user.util.StringUtils;

@Controller("userAction")
public class UserAction {

	@Resource
	private IUserService userService;

	private Map<String, Object> ajaxReturn;
	private String username;
	private String password;

	public Map<String, Object> getAjaxReturn() {
		return ajaxReturn;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() {
		// �ж��û��ύ�ı�
		if (StringUtils.isEmpty(username)) {
			this.ajaxReturn = new MapUtils().put("code",
					Constants.AjaxReturnValue.LOGIN_EMPTY_USERNAME).toMap();
			return "json";
		}

		if (StringUtils.isEmpty(password)) {
			this.ajaxReturn = new MapUtils().put("code",
					Constants.AjaxReturnValue.LOGIN_EMPTY_PASSWORD).toMap();
			return "json";
		}

		// У���û���������
		User user = userService.login(username, password);
		if (user == null) {
			this.ajaxReturn = new MapUtils().put("code",
					Constants.AjaxReturnValue.LOGIN_FAILED_DUE_TO_INFO_ERROR)
					.toMap();
			return "json";
		}

		// ���� session
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.put("user.obj", user);

		this.ajaxReturn = new MapUtils().put("code",
				Constants.AjaxReturnValue.LOGIN_SUCCESS).toMap();
		return "json";
	}

	public String logout() {
		// ��鵱ǰ�û��Ƿ��Ѿ���¼
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("user.obj");
		if (user == null) {
			this.ajaxReturn = new MapUtils().put("code",
					Constants.AjaxReturnValue.NOT_LOGIN).toMap();
			return "json";
		}

		// ��� session �еĶ���
		session.remove("user.obj");

		// ����
		this.ajaxReturn = new MapUtils().put("code",
				Constants.AjaxReturnValue.LOGOUT_SUCCESS).toMap();
		return "json";
	}
}
