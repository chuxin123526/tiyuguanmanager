package cn.wheel.tiyuguanmanager.user.util.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate4.HibernateCallback;

public class QueryEntityCountHibernateCallBack implements HibernateCallback<Long> {
	private String entityClassName;

	public QueryEntityCountHibernateCallBack(String entityClassName) {
		this.entityClassName = entityClassName;
	}

	@Override
	public Long doInHibernate(Session session) throws HibernateException {
		Query query = session.createQuery("select count(*) from " + this.entityClassName);
		long count = ((Long) query.iterate().next());
		return count;
	}
}
