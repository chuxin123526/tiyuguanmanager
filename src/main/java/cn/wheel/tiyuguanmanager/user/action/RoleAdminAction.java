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

	// �б�ҳ�룬���ղ���
	private int page;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	// ��ҳ�����������
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

	// ��ɫ���ݣ�����ҳ����ȡ
	private List<Role> roleList;

	public List<Role> getRoleList() {
		return roleList;
	}

	// ҳ�涥�������֣�ͨ���������ʵ��ҳ��ĸ���
	private String tipWord;

	public String getTipWord() {
		return tipWord;
	}

	// VO ���󣬽���ҳ�洫�����ı�
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

	// ��ǰ�����Ƿ�Ϊ�����ɫ������ʵ��ҳ��ĸ���
	private boolean isUpdateRole;

	public boolean isUpdateRole() {
		return isUpdateRole;
	}

	// ϵͳ�����е�Ȩ���б�������ʾ
	private List<PermissionItem> allPermissions;

	public List<PermissionItem> getAllPermissions() {
		return allPermissions;
	}

	// json �ķ���ֵ
	private Map<String, Object> ajaxReturn;

	public Map<String, Object> getAjaxReturn() {
		return ajaxReturn;
	}

	/**
	 * �򿪽�ɫ�б�
	 * 
	 * @return success
	 */
	public String roleList() {
		this.tipWord = "��ɫ�б�";

		// ��һ����������н�ɫ�����������ҳ��Ӧ����
		long count = roleService.getCountOfAllRoles();
		int maxPage = PagingUtils.getMaxPage((int) count, Constants.ITEM_PER_PAGE);
		makeSurePageIsInRange(1, maxPage);

		int[] bounds = PagingUtils.getPageNavigationBounds(page, maxPage, Constants.NAVI_PAGE_OFFSET);
		this.minPage = bounds[0];
		this.maxPage = bounds[1];

		this.allPages = PagingUtils.buildPageArray(this.minPage, this.maxPage);

		// �ڶ���������ҳ����㵱ǰ��ƫ����������
		int offset = PagingUtils.calcFirstOffset(page, Constants.ITEM_PER_PAGE);

		// �������������ݿ��в�ѯ��Ӧ����Ŀ
		roleList = this.roleService.list(offset, Constants.ITEM_PER_PAGE);

		return "success";
	}

	/**
	 * ��ת����ӽ�ɫҳ���
	 * 
	 * @return
	 */
	public String insertRolePage() {
		this.isUpdateRole = false;

		return "success";
	}

	/**
	 * ���������ɫ��ӵķ���
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
