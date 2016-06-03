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
import cn.wheel.tiyuguanmanager.user.exception.RoleExistException;
import cn.wheel.tiyuguanmanager.user.po.Role;
import cn.wheel.tiyuguanmanager.user.service.role.IRoleService;
import cn.wheel.tiyuguanmanager.user.util.MapUtils;
import cn.wheel.tiyuguanmanager.user.util.PagingUtils;
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
}
