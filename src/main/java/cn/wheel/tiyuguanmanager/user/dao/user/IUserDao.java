package cn.wheel.tiyuguanmanager.user.dao.user;

import java.util.List;

import cn.wheel.tiyuguanmanager.user.dao.criteria.DaoCriteria;
import cn.wheel.tiyuguanmanager.user.po.User;

public interface IUserDao {
	public List<User> list();

	public List<User> list(int offset, int count);

	public void insert(User user);

	public void update(User user);

	public void delete(User user);

	public User findById(long id);

	public List<User> findByCriteria(DaoCriteria[] criteria);
}
