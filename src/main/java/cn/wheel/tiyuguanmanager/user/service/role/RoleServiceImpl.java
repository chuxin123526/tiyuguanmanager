package cn.wheel.tiyuguanmanager.user.service.role;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wheel.tiyuguanmanager.user.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.user.dao.criteria.RoleNameCriteria;
import cn.wheel.tiyuguanmanager.user.dao.role.IRoleDao;
import cn.wheel.tiyuguanmanager.user.exception.RoleIsInUseException;
import cn.wheel.tiyuguanmanager.user.exception.RoleNotFoundException;
import cn.wheel.tiyuguanmanager.user.po.Permission;
import cn.wheel.tiyuguanmanager.user.po.Role;
import cn.wheel.tiyuguanmanager.user.vo.RoleVO;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {

	@Resource
	private IRoleDao dao;

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

		dao.insert(role);
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
		return dao.find(null);
	}

	@Transactional
	@Override
	public List<Role> list(int offset, int count) {
		return dao.find(null, offset, count);
	}

	@Transactional
	@Override
	public void updateRole(RoleVO vo) throws RoleNotFoundException {
		Role role = dao.findById(vo.getId());
		if (role == null) {
			throw new RoleNotFoundException();
		}

		if (vo.getName() != null) {
			role.setName(vo.getName());
		}

		if (vo.getPermissions() != null) {
			fillPermissionList(vo, role);
		}

		dao.update(role);
	}

	@Transactional
	@Override
	public void deleteRole(RoleVO vo) throws RoleIsInUseException, RoleNotFoundException {
		Role role = dao.findById(vo.getId());
		if (role == null) {
			throw new RoleNotFoundException();
		}

		dao.delete(role);
	}

	@Transactional
	@Override
	public List<Role> findByName(String name) {
		return dao.find(new DaoCriteria[] { new RoleNameCriteria(name) });
	}
}
