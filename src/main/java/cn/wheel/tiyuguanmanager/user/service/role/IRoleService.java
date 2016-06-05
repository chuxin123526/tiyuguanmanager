package cn.wheel.tiyuguanmanager.user.service.role;

import java.util.List;

import cn.wheel.tiyuguanmanager.common.exception.FormException;
import cn.wheel.tiyuguanmanager.user.exception.PreservedRoleException;
import cn.wheel.tiyuguanmanager.user.exception.RoleExistException;
import cn.wheel.tiyuguanmanager.user.exception.RoleIsInUseException;
import cn.wheel.tiyuguanmanager.user.exception.RoleNotFoundException;
import cn.wheel.tiyuguanmanager.user.po.Role;
import cn.wheel.tiyuguanmanager.user.vo.RoleQueryResult;
import cn.wheel.tiyuguanmanager.user.vo.RoleVO;

public interface IRoleService {
	public void insertRole(RoleVO vo) throws RoleExistException, FormException;

	public List<Role> list();

	public List<Role> list(int offset, int count);

	public void updateRole(RoleVO vo) throws RoleNotFoundException;

	public void deleteRole(RoleVO vo) throws RoleIsInUseException, RoleNotFoundException, PreservedRoleException;

	public List<Role> findByName(String name);

	public long getCountOfAllRoles();

	public Role findById(long id);

	public RoleQueryResult queryByName(String name, int page);
}
