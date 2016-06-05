package cn.wheel.tiyuguanmanager.user.dao.role;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.wheel.tiyuguanmanager.common.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.common.util.hibernate.QueryEntityCountHibernateCallBack;
import cn.wheel.tiyuguanmanager.common.util.hibernate.RowCountHibernateCallBack;
import cn.wheel.tiyuguanmanager.user.po.Role;
import cn.wheel.tiyuguanmanager.user.util.hibernate.RoleCriteriaHibernateCallback;

@Repository("roleDao")
public class RoleDaoImpl implements IRoleDao {

	@Resource
	private HibernateTemplate hibernateTemplate;

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
	public List<Role> find(DaoCriteria[] criterias) {
		return hibernateTemplate.execute(new RoleCriteriaHibernateCallback(criterias));
	}

	@Override
	public List<Role> find(DaoCriteria[] criterias, int offset, int count) {
		return this.hibernateTemplate.execute(new RoleCriteriaHibernateCallback(criterias).enablePaging(offset, count));
	}

	@Override
	public long count() {
		return this.hibernateTemplate.execute(new QueryEntityCountHibernateCallBack("Role"));
	}

	@Override
	public long count(DaoCriteria[] criterias) {
		return this.hibernateTemplate.execute(new RowCountHibernateCallBack(criterias, Role.class));
	}

}
