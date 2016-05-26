package cn.wheel.tiyuguanmanager.competition.base.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import cn.wheel.tiyuguanmanager.competition.base.IBaseDao;


public class BaseDaoImpl<T> implements IBaseDao<T>
{
	@Resource
	private SessionFactory sessionFactory;
	
	private Class clazz;

	public BaseDaoImpl()
	{
		Type genType = getClass().getGenericSuperclass();  
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        clazz = (Class) params[0];
	}

	public List<T> list() throws Exception
	{
		String hql = "from " + this.clazz.getSimpleName();
		List list = this.sessionFactory.getCurrentSession().createQuery(hql).list();

		return list;
	}

	public void add(Object entity) throws Exception
	{
		this.sessionFactory.getCurrentSession().save(this.clazz.getSimpleName(), entity);
	}

	public void deleteById(long id) throws Exception
	{
		T t = this.findById(id);

		this.sessionFactory.getCurrentSession().delete(this.clazz.getSimpleName(), t);
	}

	public void update(Object entity) throws Exception
	{
		this.sessionFactory.getCurrentSession().update(this.clazz.getSimpleName(), entity);
	}

	public T findById(long id) throws Exception
	{
		return (T) this.sessionFactory.getCurrentSession().load(this.clazz, id);
	}

	public List<T> pageList() throws Exception
	{
		// TODO Auto-generated method stub
		return null;
	}

}
