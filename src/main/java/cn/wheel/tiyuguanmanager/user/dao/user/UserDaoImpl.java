package cn.wheel.tiyuguanmanager.user.dao.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.wheel.tiyuguanmanager.user.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.user.po.User;
import cn.wheel.tiyuguanmanager.user.util.hibernate.QueryEntityCountHibernateCallBack;
import cn.wheel.tiyuguanmanager.user.util.hibernate.RowCountHibernateCallBack;
import cn.wheel.tiyuguanmanager.user.util.hibernate.UserCriteriaHibernateCallBack;

@Repository("userDao")
public class UserDaoImpl implements IUserDao {

	@Resource
	private HibernateTemplate hibernateTemplate;

	@Override
	public void insert(User user) {
		hibernateTemplate.save(user);
	}

	@Override
	public void update(User user) {
		hibernateTemplate.update(user);
	}

	@Override
	public void delete(User user) {
		hibernateTemplate.delete(user);
	}

	@Override
	public User findById(long id) {
		return hibernateTemplate.get(User.class, id);
	}

	@Override
	public List<User> find(DaoCriteria[] criteria) {
		return hibernateTemplate.execute(new UserCriteriaHibernateCallBack(criteria));
	}

	@Override
	public List<User> find(DaoCriteria[] criterias, int offset, int count) {
		return hibernateTemplate.execute(new UserCriteriaHibernateCallBack(criterias).enablePaging(offset, count));
	}

	@Override
	public long count() {
		return hibernateTemplate.execute(new QueryEntityCountHibernateCallBack("User"));
	}

	@Override
	public long count(DaoCriteria[] criterias) {
		return hibernateTemplate.execute(new RowCountHibernateCallBack(criterias, User.class));
	}

}
