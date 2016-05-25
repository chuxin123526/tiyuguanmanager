package cn.wheel.tiyuguanmanager.competition.base;

import java.util.List;

/**
* @ClassName: IBaseDao
* @Description: �ṩ��������
* @author somebody
* @date 2016��5��25�� ����8:52:21
* @param <T>
 */
public interface IBaseDao<T>
{
	public List<T> list() throws Exception;
	public void add(T entity) throws Exception ; 
	public void deleteById(long id) throws Exception ;
	public void update(T entity) throws Exception ; 
	public T findById(long id) throws Exception ; 
	public List<T> pageList() throws Exception;
}
