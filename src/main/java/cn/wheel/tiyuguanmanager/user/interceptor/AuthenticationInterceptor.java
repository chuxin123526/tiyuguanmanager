package cn.wheel.tiyuguanmanager.user.interceptor;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.wheel.tiyuguanmanager.user.constants.Constants;
import cn.wheel.tiyuguanmanager.user.po.User;
import cn.wheel.tiyuguanmanager.user.util.MapUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@Component("authenticationInterceptor")
public class AuthenticationInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;
	private static Logger logger;

	/**
	 * 用户没有登录时返回这个页面
	 */
	public static final String RETURN_LOGIN = "login";

	/**
	 * 当前的用户没有权限时返回的字符串
	 */
	public static final String PERMISSION_DENIED = "permission_denied";

	/**
	 * 返回 json 形式的操作结果
	 */
	public static final String JSON_RETURN = "json";

	static {
		logger = Logger.getLogger(AuthenticationInterceptor.class);
	}

	private String permission;
	private int[] permissionArray;
	private boolean ajaxAction;
	private Map<String, Object> ajaxJson;

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;

		if (this.permission.contains(",")) {
			String[] permissionItems = this.permission.split(",");
			this.permissionArray = new int[permissionItems.length];

			for (int i = 0; i < this.permissionArray.length; i++) {
				try {
					this.permissionArray[i] = Integer.parseInt(permissionItems[i]);
				} catch (NumberFormatException e) {
					logger.error("parse permission string failed.", e);
				}
			}
		} else {
			this.permissionArray = new int[1];
			try {
				this.permissionArray[0] = Integer.parseInt(this.permission);
			} catch (NumberFormatException e) {
				logger.error("parse permission string failed.", e);
			}
		}
	}

	public boolean isAjaxAction() {
		return ajaxAction;
	}

	public void setAjaxAction(boolean ajaxAction) {
		this.ajaxAction = ajaxAction;
	}

	public Map<String, Object> getAjaxJson() {
		return ajaxJson;
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 判断是否已经登录
		Map<String, Object> session = ActionContext.getContext().getSession();
		User user = (User) session.get("user");
		if (user == null) {
			if (!ajaxAction) {
				return RETURN_LOGIN;
			} else {
				this.ajaxJson = new MapUtils().put("code", Constants.AjaxReturnValue.NOT_LOGIN).toMap();
				return "json";
			}
		}

		// 判断当前的登录用户是否具有相应的权限
		boolean hasPermission = false;
		for (int permission : this.permissionArray) {
			if (user.hasPermission(permission)) {
				hasPermission = true;
				break;
			}
		}

		if (!hasPermission) {
			if (ajaxAction) {
				this.ajaxJson = new MapUtils().put("code", Constants.AjaxReturnValue.PERMISSION_DENIED).toMap();
				return "json";
			} else {
				return PERMISSION_DENIED;
			}
		}

		// 如果有权限就放行
		return invocation.invoke();
	}

}
