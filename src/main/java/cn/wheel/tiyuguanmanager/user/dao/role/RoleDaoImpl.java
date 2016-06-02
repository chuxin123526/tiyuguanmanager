package cn.wheel.tiyuguanmanager.user.dao.role;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.wheel.tiyuguanmanager.user.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.user.po.Role;
import cn.wheel.tiyuguanmanager.user.util.hibernate.PaginationHibernateCallBack;
import cn.wheel.tiyuguanmanager.user.util.hibernate.RoleCriteriaHibernateCallback;

@Repository("roleDao")
public class RoleDaoImpl implements IRoleDao {

	@Resource
	private HibernateTemplate hibernateTemplate;

	@Override
	public List<Role> list() {
		return hibernateTemplate.loadAll(Role.class);
	}

	@Override
	public List<Role> list(int offset, int count) {
		return hibernateTemplate.execute(new PaginationHibernateCallBack<>(
				offset, count, Role.class));
	}

	@Override
	public void insert(Role role) {
		hibernateTemplate.save(role);
	}

	@Override
	public void update(Role role) {
		hibernateTemplate.update(role);
	}

	@Override
	public void delete(Role role) {
		hibernateTemplate.delete(role);
	}

	@Override
	public Role findById(long id) {
		return hibernateTemplate.get(Role.class, id);
	}

	@Override
	public List<Role> findByCriteria(DaoCriteria[] criterias) {
		return hibernateTemplate.execute(new RoleCriteriaHibernateCallback(
				criterias));
	}

}
