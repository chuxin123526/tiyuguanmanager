package cn.wheel.tiyuguanmanager.user.service.role;

import java.util.List;

import cn.wheel.tiyuguanmanager.user.exception.RoleIsInUseException;
import cn.wheel.tiyuguanmanager.user.exception.RoleNotFoundException;
import cn.wheel.tiyuguanmanager.user.po.Role;
import cn.wheel.tiyuguanmanager.user.vo.RoleVO;

public interface IRoleService {
	public void insertRole(RoleVO vo);

	public List<Role> list();

	public List<Role> list(int offset, int count);

	public void updateRole(RoleVO vo) throws RoleNotFoundException;

	public void deleteRole(RoleVO vo) throws RoleIsInUseException,
			RoleNotFoundException;
	
	public List<Role> findByName(String name);
}