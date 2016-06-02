package cn.wheel.tiyuguanmanager.user.util.hibernate;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;

/**
 * Hibernate ∑÷“≥≤È—Ø
 * 
 * @author DENG YURONG
 * 
 * @param <T>
 */
public class PaginationHibernateCallBack<T> implements
		HibernateCallback<List<T>> {

	private int offset;
	private int count;
	private Class<? extends T> clazz;

	public PaginationHibernateCallBack(int offset, int count,
			Class<? extends T> clazz) {
		super();
		this.offset = offset;
		this.count = count;
		this.clazz = clazz;
	}

	public int getOffset() {
		return offset;
	}

	public PaginationHibernateCallBack<T> setOffset(int offset) {
		this.offset = offset;
		return this;
	}

	public int getCount() {
		return count;
	}

	public PaginationHibernateCallBack<T> setCount(int count) {
		this.count = count;
		return this;
	}

	public Class<? extends T> getClazz() {
		return clazz;
	}

	public void setClazz(Class<? extends T> clazz) {
		this.clazz = clazz;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> doInHibernate(Session session) throws HibernateException {
		Criteria criteria = session.createCriteria(this.clazz)
				.setFirstResult(offset).setMaxResults(count);
		return criteria.list();
	}

}
