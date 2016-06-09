package cn.wheel.tiyuguanmanager.user.service.role;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wheel.tiyuguanmanager.common.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.common.exception.FormException;
import cn.wheel.tiyuguanmanager.user.constants.UserConstants;
import cn.wheel.tiyuguanmanager.user.dao.criteria.RoleNameCriteria;
import cn.wheel.tiyuguanmanager.user.dao.criteria.UserRoleNameCriteria;
import cn.wheel.tiyuguanmanager.user.dao.role.IRoleDao;
import cn.wheel.tiyuguanmanager.user.dao.user.IUserDao;
import cn.wheel.tiyuguanmanager.user.exception.PreservedRoleException;
import cn.wheel.tiyuguanmanager.user.exception.RoleExistException;
import cn.wheel.tiyuguanmanager.user.exception.RoleIsInUseException;
import cn.wheel.tiyuguanmanager.user.exception.RoleNotFoundException;
import cn.wheel.tiyuguanmanager.user.po.Permission;
import cn.wheel.tiyuguanmanager.user.po.Role;
import cn.wheel.tiyuguanmanager.user.po.User;
import cn.wheel.tiyuguanmanager.user.util.PagingUtils;
import cn.wheel.tiyuguanmanager.user.vo.RoleQueryResult;
import cn.wheel.tiyuguanmanager.user.vo.RoleVO;
import cn.wheel.tiyuguanmanager.user.vo.validator.RoleInsertVOValidator;
import cn.wheel.tiyuguanmanager.user.vo.validator.exception.VOTypeNotMatch;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {

	@Resource
	private IRoleDao roleDao;

	@Resource
	private IUserDao userDao;

	@Transactional
	@Override
	public void insertRole(RoleVO vo) throws RoleExistException, FormException {
		if (vo == null) {
			throw new FormException();
		}

		// 校验表单
		RoleInsertVOValidator validator = new RoleInsertVOValidator();
		boolean validated = true;

		try {
			validated = validator.validate(vo);
		} catch (VOTypeNotMatch e) {

		}

		if (!validated) {
			throw new FormException(validator.getErrorMessages());
		}

		// 判断同名的角色是否已经存在
		List<Role> list = roleDao.find(new DaoCriteria[] { new RoleNameCriteria(vo.getName()) });
		if (list.size() > 0) {
			throw new RoleExistException();
		}

		// 组装 po
		Role role = new Role();
		role.setName(vo.getName());
		role.setPermissions(new HashSet<Permission>());

		fillPermissionList(vo, role);

		roleDao.insert(role);
	}

	private void fillPermissionList(RoleVO vo, Role role) {
		role.getPermissions().clear();

		for (Integer permissionInt : vo.getPermissions()) {
			Permission permission = new Permission();
			permission.setType(permissionInt);
			role.getPermissions().add(permission);
		}
	}

	@Transactional
	@Override
	public List<Role> list() {
		return roleDao.find(null);
	}

	@Transactional
	@Override
	public List<Role> list(int offset, int count) {
		return roleDao.find(null, offset, count);
	}

	@Transactional
	@Override
	public void updateRole(RoleVO vo) throws RoleNotFoundException {
		Role role = roleDao.findById(vo.getId());
		if (role == null) {
			throw new RoleNotFoundException();
		}

		if (vo.getName() != null) {
			role.setName(vo.getName());
		}

		if (vo.getPermissions() != null) {
			fillPermissionList(vo, role);
		}

		roleDao.update(role);
	}

	@Transactional
	@Override
	public void deleteRole(RoleVO vo) throws RoleIsInUseException, RoleNotFoundException, PreservedRoleException {
		// 寻找角色
		Role role = roleDao.findById(vo.getId());
		if (role == null) {
			throw new RoleNotFoundException();
		}

		// 判断是否为保留角色
		if ("注册用户".equals(role.getName()) || "认证用户".equals(role.getName())) {
			throw new PreservedRoleException(role.getName());
		}

		// 查看该角色下是否拥有用户
		List<User> userList = userDao.find(new DaoCriteria[] { new UserRoleNameCriteria(role.getName()) });
		if (userList.size() > 0) {
			throw new RoleIsInUseException(role.getName());
		}

		// 删除角色
		roleDao.delete(role);
	}

	@Transactional
	@Override
	public List<Role> findByName(String name) {
		return roleDao.find(new DaoCriteria[] { new RoleNameCriteria(name) });
	}

	@Transactional
	@Override
	public long getCountOfAllRoles() {
		return roleDao.count();
	}

	@Transactional
	@Override
	public Role findById(long id) {
		return roleDao.findById(id);
	}

	@Transactional
	@Override
	public RoleQueryResult queryByName(String name, int page) {
		RoleQueryResult result = new RoleQueryResult();

		DaoCriteria[] criterias = new DaoCriteria[] { new RoleNameCriteria(name, false) };

		// 查询总数量
		long totalCount = this.roleDao.count(criterias);
		int maxPage = PagingUtils.getMaxPage((int) totalCount, UserConstants.ITEM_PER_PAGE);

		result.setTotalCount(totalCount);
		result.setMaxPage(maxPage);

		// 查询指定分页的数据
		List<Role> list = this.roleDao.find(criterias, PagingUtils.calcFirstOffset(page, UserConstants.ITEM_PER_PAGE), UserConstants.ITEM_PER_PAGE);
		result.setCurrentPage(page);
		result.setCurragePageItem(list.size());
		result.setResult(list);
		result.setCriterias(criterias);

		return result;
	}
}
