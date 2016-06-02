package cn.wheel.tiyuguanmanager.user.dao.role;

import java.util.List;

import cn.wheel.tiyuguanmanager.user.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.user.po.Role;

public interface IRoleDao {
	public List<Role> list();

	public List<Role> list(int offset, int count);

	public void insert(Role role);

	public void update(Role role);

	public void delete(Role role);

	public Role findById(long id);

	public List<Role> findByCriteria(DaoCriteria[] criterias);
}
