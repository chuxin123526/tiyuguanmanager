package cn.wheel.tiyuguanmanager.user.service.role;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wheel.tiyuguanmanager.user.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.user.dao.criteria.RoleNameCriteria;
import cn.wheel.tiyuguanmanager.user.dao.criteria.UserRoleNameCriteria;
import cn.wheel.tiyuguanmanager.user.dao.role.IRoleDao;
import cn.wheel.tiyuguanmanager.user.dao.user.IUserDao;
import cn.wheel.tiyuguanmanager.user.exception.RoleIsInUseException;
import cn.wheel.tiyuguanmanager.user.exception.RoleNotFoundException;
import cn.wheel.tiyuguanmanager.user.po.Permission;
import cn.wheel.tiyuguanmanager.user.po.Role;
import cn.wheel.tiyuguanmanager.user.po.User;
import cn.wheel.tiyuguanmanager.user.vo.RoleVO;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {

	@Resource
	private IRoleDao roleDao;

	@Resource
	private IUserDao userDao;

	@Transactional
	@Override
	public void insertRole(RoleVO vo) {
		if (vo == null) {
			throw new IllegalArgumentException("vo is null");
		}

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
	public void deleteRole(RoleVO vo) throws RoleIsInUseException, RoleNotFoundException {
		// Ѱ�ҽ�ɫ
		Role role = roleDao.findById(vo.getId());
		if (role == null) {
			throw new RoleNotFoundException();
		}

		// �鿴�ý�ɫ���Ƿ�ӵ���û�
		List<User> userList = userDao.find(new DaoCriteria[] { new UserRoleNameCriteria(role.getName()) });
		if (userList.size() > 0) {
			throw new RoleIsInUseException();
		}

		// ɾ����ɫ
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
}
