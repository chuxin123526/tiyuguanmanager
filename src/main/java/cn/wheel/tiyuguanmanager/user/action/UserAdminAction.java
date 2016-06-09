package cn.wheel.tiyuguanmanager.user.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.wheel.tiyuguanmanager.common.exception.FormException;
import cn.wheel.tiyuguanmanager.user.constants.UserConstants;
import cn.wheel.tiyuguanmanager.user.exception.RoleNotFoundException;
import cn.wheel.tiyuguanmanager.user.exception.UserExistException;
import cn.wheel.tiyuguanmanager.user.exception.UserNotExistException;
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

	// ��ʾҳ��Ĺ��ܣ�����ҳ��ĸ���
	// 1 -- �û���Ϣ���
	// 2 -- �û���Ϣ��֤
	// 3 -- δ��֤�û���Ϣ�б�
	private int function;

	public int getFunction() {
		return function;
	}

	public void setFunction(int function) {
		this.function = function;
	}

	// ���ڽ��ú������û��˻� action �����û����
	private long userId;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	// ����û���Ϣʱ��������Ϣ���û� po ����
	private User user;

	public User getUser() {
		return user;
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
			this.ajaxReturn = MapUtils.generatorCodeMap(UserConstants.AjaxReturnValue.USER_INSERT_SUCCESS);
			return "json";
		} catch (FormException e) {
			this.ajaxReturn = e.getErrorMessages();
			this.ajaxReturn.put("code", UserConstants.AjaxReturnValue.FORM_EXCEPTION);
			return "json";
		} catch (UserExistException e) {
			this.ajaxReturn = MapUtils.generatorCodeMap(UserConstants.AjaxReturnValue.USER_EXIST);
			return "json";
		} catch (RoleNotFoundException e) {
			this.ajaxReturn = MapUtils.generatorCodeMap(UserConstants.AjaxReturnValue.ROLE_INVAILD_ROLE_ID);
			return "json";
		}
	}

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
		this.tipWord = "��ѯ���";

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

	/**
	 * �����û��˻�
	 * 
	 * @return
	 */
	public String enableUser() {
		try {
			userService.enableUserAccount(userId);

			this.ajaxReturn = MapUtils.generatorCodeMap(UserConstants.AjaxReturnValue.USER_ENABLE_SUCCESS);
			return "json";
		} catch (UserNotExistException e) {
			this.ajaxReturn = MapUtils.generatorCodeMap(UserConstants.AjaxReturnValue.USER_NOT_EXIST);
			return "json";
		}
	}

	/**
	 * �����û��˻�
	 * 
	 * @return
	 */
	public String forbidUser() {
		try {
			userService.forbidUserAccount(userId);

			this.ajaxReturn = MapUtils.generatorCodeMap(UserConstants.AjaxReturnValue.USER_FORBID_SUCCESS);
			return "json";
		} catch (UserNotExistException e) {
			this.ajaxReturn = MapUtils.generatorCodeMap(UserConstants.AjaxReturnValue.USER_NOT_EXIST);
			return "json";
		}
	}

	/**
	 * ����û���Ϣҳ��
	 * 
	 * @return
	 */
	public String updateUserPage() {
		this.tipWord = "�û���Ϣ���";
		this.function = 1;
		this.user = this.userService.findUserById(userId);
		this.roleList = this.roleService.list();

		return "success";
	}

	/**
	 * �����û���Ϣ���
	 * 
	 * @return
	 */
	public String updateUser() {
		try {
			this.userService.updateUser(create);

			this.ajaxReturn = MapUtils.generatorCodeMap(UserConstants.AjaxReturnValue.USER_UPDATE_SUCCESS);
			return "json";
		} catch (FormException e) {
			this.ajaxReturn = e.getErrorMessages();
			this.ajaxReturn.put("code", UserConstants.AjaxReturnValue.FORM_EXCEPTION);

			return "json";
		} catch (UserNotExistException e) {
			this.ajaxReturn = MapUtils.generatorCodeMap(UserConstants.AjaxReturnValue.USER_NOT_EXIST);
			return "json";
		} catch (UserExistException e) {
			this.ajaxReturn = MapUtils.generatorCodeMap(UserConstants.AjaxReturnValue.USER_EXIST);
			return "json";
		} catch (RoleNotFoundException e) {
			this.ajaxReturn = MapUtils.generatorCodeMap(UserConstants.AjaxReturnValue.ROLE_INVAILD_ROLE_ID);
			return "json";
		}
	}

	/**
	 * �����û�����Ϣ��֤���󷽷�
	 * 
	 * @return
	 */
	public String verifyUser() {
		try {
			this.userService.checkUser(userId, true);

			this.ajaxReturn = MapUtils.generatorCodeMap(UserConstants.AjaxReturnValue.USER_VERIFY_SUCCESS);
			return "json";
		} catch (UserNotExistException e) {
			this.ajaxReturn = MapUtils.generatorCodeMap(UserConstants.AjaxReturnValue.USER_NOT_EXIST);
			return "json";
		}
	}

	/**
	 * �������û���Ϣ��֤����ķ���
	 * 
	 * @return
	 */
	public String deverifyUser() {
		try {
			this.userService.checkUser(userId, false);

			this.ajaxReturn = MapUtils.generatorCodeMap(UserConstants.AjaxReturnValue.USER_VERIFY_CANCEL_SUCCESS);
			return "json";
		} catch (UserNotExistException e) {
			this.ajaxReturn = MapUtils.generatorCodeMap(UserConstants.AjaxReturnValue.USER_NOT_EXIST);
			return "json";
		}
	}

	/**
	 * ��ת����ʾδ�����û���֤���û��б�
	 * 
	 * @return
	 */
	public String verifyUserPage() {
		this.tipWord = "�û���Ϣ��֤";
		this.function = 3;

		List<Role> list = this.roleService.findByName("ע���û�");
		if (list.size() == 0) {
			return "error";
		}

		UserQueryVO queryVO = new UserQueryVO();
		queryVO.setCriteria(new int[] { 1 });
		queryVO.setRoleId(list.get(0).getRoleId());
		queryVO.setForbidden(1);

		this.result = this.userService.queryUser(queryVO);
		this.queryShowback = result.getShowback();
		this.userList = result.getResult();

		this.maxPage = result.getMaxPage();
		makeSurePageIsInRange(1, this.maxPage);

		int[] bounds = PagingUtils.getPageNavigationBounds(this.page, this.maxPage, 3);
		this.minPage = bounds[0];
		this.maxPage = bounds[1];
		this.allPages = PagingUtils.buildPageArray(this.minPage, this.maxPage);

		return "success";
	}

	/**
	 * �����û���֤������ҳ��
	 * 
	 * @return
	 */
	public String verifyUserDetailedPage() {
		this.tipWord = "�û���Ϣ��֤";
		this.function = 2;
		this.user = this.userService.findUserById(this.userId);
		
		return "success";
	}
}
