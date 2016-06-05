package cn.wheel.tiyuguanmanager.user.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.wheel.tiyuguanmanager.user.constants.Constants;
import cn.wheel.tiyuguanmanager.user.exception.FormException;
import cn.wheel.tiyuguanmanager.user.exception.RoleNotFoundException;
import cn.wheel.tiyuguanmanager.user.exception.UserExistException;
import cn.wheel.tiyuguanmanager.user.po.Role;
import cn.wheel.tiyuguanmanager.user.po.User;
import cn.wheel.tiyuguanmanager.user.service.role.IRoleService;
import cn.wheel.tiyuguanmanager.user.service.user.IUserService;
import cn.wheel.tiyuguanmanager.user.util.MapUtils;
import cn.wheel.tiyuguanmanager.user.util.PagingUtils;
import cn.wheel.tiyuguanmanager.user.vo.UserQueryResult;
import cn.wheel.tiyuguanmanager.user.vo.UserQueryShowback;
import cn.wheel.tiyuguanmanager.user.vo.UserQueryVO;
import cn.wheel.tiyuguanmanager.user.vo.UserVO;

@Controller("userAdminAction")
@Scope("prototype")
public class UserAdminAction {

	@Resource
	private IRoleService roleService;

	@Resource
	private IUserService userService;

	// 列表，页码，接收参数
	private int page;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	// 分页导航相关数据
	private int minPage;
	private int maxPage;
	private int allPages[];

	public int getMinPage() {
		return minPage;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public int[] getAllPages() {
		return allPages;
	}

	// 角色数据，用于页面提取
	private List<Role> roleList;

	public List<Role> getRoleList() {
		return roleList;
	}

	// 页面顶部的文字，通过这个属性实现页面的复用
	private String tipWord;

	public String getTipWord() {
		return tipWord;
	}

	// 接收创建用户表单的 VO 对象
	private UserVO create;

	public UserVO getCreate() {
		return create;
	}

	public void setCreate(UserVO create) {
		this.create = create;
	}

	// 用作 ajax 返回
	private Map<String, Object> ajaxReturn;

	public Map<String, Object> getAjaxReturn() {
		return ajaxReturn;
	}

	// 接收用户查询条件的表单
	private UserQueryVO query;

	public UserQueryVO getQuery() {
		return query;
	}

	public void setQuery(UserQueryVO query) {
		this.query = query;
	}

	// 用于回传查询条件的对象
	private UserQueryShowback queryShowback;

	public UserQueryShowback getQueryShowback() {
		return queryShowback;
	}

	public void setQueryShowback(UserQueryShowback queryShowBack) {
		this.queryShowback = queryShowBack;
	}

	// 用于接收用户查询结果的集合类
	private List<User> userList;

	public List<User> getUserList() {
		return userList;
	}

	// 查询结果
	private UserQueryResult result;

	public UserQueryResult getResult() {
		return result;
	}

	/**
	 * 用户管理模块首页
	 * 
	 * @return success
	 */
	public String index() {
		return "success";
	}

	/**
	 * 创建用户页面
	 * 
	 * @return
	 */
	public String newUserPage() {
		this.roleList = this.roleService.list();

		return "success";
	}

	/**
	 * 处理创建用户的请求
	 * 
	 * @return
	 */
	public String newUser() {
		try {
			this.userService.insertUser(create);
			this.ajaxReturn = MapUtils.generatorCodeMap(Constants.AjaxReturnValue.USER_INSERT_SUCCESS);
			return "json";
		} catch (FormException e) {
			this.ajaxReturn = e.getErrorMessages();
			this.ajaxReturn.put("code", Constants.AjaxReturnValue.FORM_EXCEPTION);
			return "json";
		} catch (UserExistException e) {
			this.ajaxReturn = MapUtils.generatorCodeMap(Constants.AjaxReturnValue.USER_EXIST);
			return "json";
		} catch (RoleNotFoundException e) {
			this.ajaxReturn = MapUtils.generatorCodeMap(Constants.AjaxReturnValue.ROLE_INVAILD_ROLE_ID);
			return "json";
		}
	}

	@SuppressWarnings("unused")
	private void makeSurePageIsInRange(int min, int max) {
		if (page < min) {
			page = min;
		} else if (page > max) {
			page = max;
		}
	}

	/**
	 * 打开用户查询页面
	 * 
	 * @return
	 */
	public String userQueryPage() {
		this.roleList = this.roleService.list();

		return "success";
	}

	/**
	 * 用于相应查询用户请求的方法
	 * 
	 * @return
	 */
	public String userQuery() {
		this.result = this.userService.queryUser(query);
		this.queryShowback = result.getShowback();
		this.userList = result.getResult();

		this.maxPage = result.getMaxPage();
		int[] bounds = PagingUtils.getPageNavigationBounds(query.getPage(), this.maxPage, 3);
		this.minPage = bounds[0];
		this.maxPage = bounds[1];
		this.allPages = PagingUtils.buildPageArray(this.minPage, this.maxPage);

		return "success";
	}
}
