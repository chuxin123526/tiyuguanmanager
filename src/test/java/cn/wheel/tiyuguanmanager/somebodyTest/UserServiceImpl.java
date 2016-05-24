package cn.wheel.tiyuguanmanager.somebodyTest;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements IUserService
{
	@Resource
	private SessionFactory sessionFactory ; 

	public void add(User user) 
	{
		System.out.println(this.sessionFactory.getCurrentSession().save(user)) ; 
		int j = 1/0 ; 
		System.out.println(this.sessionFactory.getCurrentSession().save(user)) ; 
		
	}

}
