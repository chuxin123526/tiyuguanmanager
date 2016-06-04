package cn.wheel.tiyuguanmanager.user.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.wheel.tiyuguanmanager.constants.PermissionConstants;
import cn.wheel.tiyuguanmanager.constants.PermissionItem;
import cn.wheel.tiyuguanmanager.user.constants.Constants;
import cn.wheel.tiyuguanmanager.user.exception.FormException;
import cn.wheel.tiyuguanmanager.user.exception.PreservedRoleException;
import cn.wheel.tiyuguanmanager.user.exception.RoleExistException;
import cn.wheel.tiyuguanmanager.user.exception.RoleIsInUseException;
import cn.wheel.tiyuguanmanager.user.exception.RoleNotFoundException;
import cn.wheel.tiyuguanmanager.user.po.Role;
import cn.wheel.tiyuguanmanager.user.service.role.IRoleService;
import cn.wheel.tiyuguanmanager.user.util.MapUtils;
import cn.wheel.tiyuguanmanager.user.util.PagingUtils;
import cn.wheel.tiyuguanmanager.user.vo.RoleQueryResult;
import cn.wheel.tiyuguanmanager.user.vo.RoleVO;

@Controller("roleAdminAction")
@Scope("prototype")
public class RoleAdminAction {

	@Resource
	private IRoleService roleService;

	public RoleAdminAction() {
		this.allPermissions = PermissionConstants.getAllPermissionsList();
	}

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

	// VO 对象，接收页面传过来的表单
	private RoleVO form;

	public void setForm(RoleVO form) {
		this.form = form;
	}

	public RoleVO getForm() {
		return form;
	}

	private void makeSurePageIsInRange(int min, int max) {
		if (page < min) {
			page = min;
		} else if (page > max) {
			page = max;
		}
	}

	// 当前操作是否为变更角色操作，实现页面的复用
	private boolean isUpdateRole;

	public boolean isUpdateRole() {
		return isUpdateRole;
	}

	// 系统中所有的权限列表，用于显示
	private List<PermissionItem> allPermissions;

	public List<PermissionItem> getAllPermissions() {
		return allPermissions;
	}

	// json 的返回值
	private Map<String, Object> ajaxReturn;

	public Map<String, Object> getAjaxReturn() {
		return ajaxReturn;
	}

	// 用于描述上一步的步骤
	// 1 -- 添加
	// 2 -- 删除
	// 3 -- 修改
	// 4 -- 查询
	private int from;

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	// 用于在错误页面显示错误信息
	private String errMsg;

	public String getErrMsg() {
		return errMsg;
	}

	// 用于角色数据的回显
	private Role role;

	public Role getRole() {
		return role;
	}

	// 保存业务层传来的原始数据
	private RoleQueryResult result;

	public RoleQueryResult getResult() {
		return result;
	}

	/**
	 * 打开角色列表
	 * 
	 * @return success
	 */
	public String roleList() {
		this.tipWord = "角色列表";

		// 第一步：获得所有角色数量，计算分页相应参数
		long count = roleService.getCountOfAllRoles();
		int maxPage = PagingUtils.getMaxPage((int) count, Constants.ITEM_PER_PAGE);

		// 如果页码是负数，则从倒数开始算
		if (this.page < 0) {
			this.page = maxPage + this.page + 1;
		}
		makeSurePageIsInRange(1, maxPage);

		int[] bounds = PagingUtils.getPageNavigationBounds(page, maxPage, Constants.NAVI_PAGE_OFFSET);
		this.minPage = bounds[0];
		this.maxPage = bounds[1];

		this.allPages = PagingUtils.buildPageArray(this.minPage, this.maxPage);

		// 第二步：根据页码计算当前的偏移量和数量
		int offset = PagingUtils.calcFirstOffset(page, Constants.ITEM_PER_PAGE);

		// 第三步：从数据库中查询相应的项目
		roleList = this.roleService.list(offset, Constants.ITEM_PER_PAGE);

		return "success";
	}

	/**
	 * 跳转到添加角色页面的
	 * 
	 * @return
	 */
	public String insertRolePage() {
		this.isUpdateRole = false;

		return "success";
	}

	/**
	 * 真正处理角色添加的方法
	 * 
	 * @return
	 */
	public String insertRole() {
		this.isUpdateRole = false;

		try {
			this.roleService.insertRole(form);
			this.ajaxReturn = new MapUtils().put("code", Constants.AjaxReturnValue.ROLE_CREATE_SUCCESS).toMap();

			return "json";
		} catch (RoleExistException e) {
			this.ajaxReturn = new MapUtils().put("code", Constants.AjaxReturnValue.ROLE_EXIST).toMap();
			return "json";
		} catch (FormException e) {
			this.ajaxReturn = e.getErrorMessages();
			this.ajaxReturn.put("code", Constants.AjaxReturnValue.FORM_EXCEPTION);
			return "json";
		}
	}

	/**
	 * 删除角色的方法
	 * 
	 * @return
	 */
	public String deleteRole() {
		try {
			this.roleService.deleteRole(form);

			this.ajaxReturn = new MapUtils().put("code", Constants.AjaxReturnValue.ROLE_DELETE_SUCCESS).toMap();
			return "json";
		} catch (RoleIsInUseException e) {
			this.ajaxReturn = new MapUtils().put("code", Constants.AjaxReturnValue.ROLE_IS_IN_USE).put("name", e.getRoleName()).toMap();
			return "json";
		} catch (RoleNotFoundException e) {
			this.ajaxReturn = new MapUtils().put("code", Constants.AjaxReturnValue.ROLE_INVAILD_ROLE_ID).toMap();
			return "json";
		} catch (PreservedRoleException e) {
			this.ajaxReturn = new MapUtils().put("code", Constants.AjaxReturnValue.ROLE_PRESERVED).put("name", e.getRoleName()).toMap();
			return "json";
		}
	}

	/**
	 * 打开角色变更页面的方法
	 * 
	 * @return
	 */
	public String updateRolePage() {
		this.isUpdateRole = true;

		this.role = this.roleService.findById(form.getId());
		if (this.role == null) {
			this.errMsg = "找不到编号为 " + form.getId() + " 的用户角色";
			return "error";
		}

		return "success";
	}

	/**
	 * 真正处理角色修改的位置
	 * 
	 * @return
	 */
	public String updateRole() {
		try {
			this.roleService.updateRole(form);

			this.ajaxReturn = new MapUtils().put("code", Constants.AjaxReturnValue.ROLE_UPDATE_SUCCESS).toMap();
			return "json";
		} catch (RoleNotFoundException e) {
			this.ajaxReturn = new MapUtils().put("code", Constants.AjaxReturnValue.ROLE_INVAILD_ROLE_ID).toMap();
			return "json";
		}
	}

	/**
	 * 查询页面
	 * 
	 * @return
	 */
	public String roleQueryPage() {
		return "success";
	}

	/**
	 * 查询页面的处理方法
	 * 
	 * @return
	 */
	public String roleQuery() {
		this.tipWord = "查询结果";
		this.from = 4;

		System.out.println(form.getName());

		// 查询结果
		RoleQueryResult result = this.roleService.queryByName(form.getName(), this.page);
		if (this.page > result.getMaxPage()) {
			this.page = result.getMaxPage();
			result = this.roleService.queryByName(form.getName(), this.page);
		}

		this.roleList = result.getResult();

		// 计算分页导航参数
		int[] bounds = PagingUtils.getPageNavigationBounds(this.page, result.getMaxPage(), Constants.NAVI_PAGE_OFFSET);
		this.minPage = bounds[0];
		this.maxPage = bounds[1];

		this.allPages = PagingUtils.buildPageArray(this.minPage, this.maxPage);

		// 将查询参数传递过去
		this.result = result;

		return "success";
	}
}
