package cn.wheel.tiyuguanmanager.user.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.wheel.tiyuguanmanager.user.po.Role;
import cn.wheel.tiyuguanmanager.user.service.role.IRoleService;

@Controller("userAdminAction")
@Scope("prototype")
public class UserAdminAction {

	@Resource
	private IRoleService roleService;

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

	/**
	 * �û�����ģ����ҳ
	 * 
	 * @return success
	 */
	public String index() {
		return "success";
	}

	@SuppressWarnings("unused")
	private void makeSurePageIsInRange(int min, int max) {
		if (page < min) {
			page = min;
		} else if (page > max) {
			page = max;
		}
	}
}
