package cn.wheel.tiyuguanmanager.common.util.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;

public abstract class PaginationHibernateCriteriaCallback<T> implements HibernateCallback<List<T>> {
	private boolean isPagingQuery;
	private int offset;
	private int count;

	private Class<? extends Object> clazz;

	public PaginationHibernateCriteriaCallback(Class<? extends Object> clazz) {
		this.clazz = clazz;
		this.isPagingQuery = false;
	}

	public boolean isPagingQuery() {
		return isPagingQuery;
	}

	public int getOffset() {
		return offset;
	}

	public int getCount() {
		return count;
	}

	public PaginationHibernateCriteriaCallback<T> enablePaging(int offset, int count) {
		this.isPagingQuery = true;
		this.offset = offset;
		this.count = count;

		return this;
	}

	public PaginationHibernateCriteriaCallback<T> disablePaging() {
		this.isPagingQuery = false;
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> doInHibernate(Session session) throws HibernateException {
		Criteria criteria = session.createCriteria(this.clazz);

		if (this.isPagingQuery) {
			criteria.setFirstResult(offset).setMaxResults(count);
		}

		doProcessCriteria(criteria);

		return criteria.list();
	}

	public abstract void doProcessCriteria(Criteria criteria);
}
