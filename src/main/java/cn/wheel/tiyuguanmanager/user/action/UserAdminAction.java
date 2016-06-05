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

	// ���մ����û����� VO ����
	private UserVO create;

	public UserVO getCreate() {
		return create;
	}

	public void setCreate(UserVO create) {
		this.create = create;
	}

	// ���� ajax ����
	private Map<String, Object> ajaxReturn;

	public Map<String, Object> getAjaxReturn() {
		return ajaxReturn;
	}

	// �����û���ѯ�����ı�
	private UserQueryVO query;

	public UserQueryVO getQuery() {
		return query;
	}

	public void setQuery(UserQueryVO query) {
		this.query = query;
	}

	// ���ڻش���ѯ�����Ķ���
	private UserQueryShowback queryShowback;

	public UserQueryShowback getQueryShowback() {
		return queryShowback;
	}

	public void setQueryShowback(UserQueryShowback queryShowBack) {
		this.queryShowback = queryShowBack;
	}

	// ���ڽ����û���ѯ����ļ�����
	private List<User> userList;

	public List<User> getUserList() {
		return userList;
	}

	// ��ѯ���
	private UserQueryResult result;

	public UserQueryResult getResult() {
		return result;
	}

	/**
	 * �û�����ģ����ҳ
	 * 
	 * @return success
	 */
	public String index() {
		return "success";
	}

	/**
	 * �����û�ҳ��
	 * 
	 * @return
	 */
	public String newUserPage() {
		this.roleList = this.roleService.list();

		return "success";
	}

	/**
	 * �������û�������
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
	 * ���û���ѯҳ��
	 * 
	 * @return
	 */
	public String userQueryPage() {
		this.roleList = this.roleService.list();

		return "success";
	}

	/**
	 * ������Ӧ��ѯ�û�����ķ���
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
