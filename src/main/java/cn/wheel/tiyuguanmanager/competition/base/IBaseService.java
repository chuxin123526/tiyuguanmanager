package cn.wheel.tiyuguanmanager.competition.base;

import java.util.List;

public interface IBaseService<T>
{
	public List<T> list() throws Exception; 
	public List<T> pageList() throws Exception; 
	public void add(T entity) throws Exception ;
	public void deleteById(long id) throws Exception ; 
	public void update(T entity) throws Exception ;
	public T  findById(long id) throws Exception ;
}
