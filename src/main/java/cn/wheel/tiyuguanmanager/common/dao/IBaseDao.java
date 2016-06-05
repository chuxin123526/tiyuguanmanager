package cn.wheel.tiyuguanmanager.common.dao;

import java.util.List;

import cn.wheel.tiyuguanmanager.common.dao.criteria.DaoCriteria;

public interface IBaseDao<T> {
	public void insert(T entity);

	public void update(T entity);

	public void delete(T entitiy);

	public T findById(long id);

	public long count();

	public long count(DaoCriteria[] criterias);

	public List<T> find(DaoCriteria[] criterias);

	public List<T> find(DaoCriteria[] criterias, int offset, int count);
}
