package cn.wheel.tiyuguanmanager.competition.base;

import java.util.List;

/**
* @ClassName: IBaseDao
* @Description: 提供基础操作
* @author somebody
* @date 2016年5月25日 上午8:52:21
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
