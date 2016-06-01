package cn.wheel.tiyuguanmanager.somebodyTest;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.wheel.tiyuguanmanager.competition.dao.ICompetitionDao;
import cn.wheel.tiyuguanmanager.competition.po.Competition;
import cn.wheel.tiyuguanmanager.competition.service.ICompetitionService;

public class Test
{
	@org.junit.Test
	public void testSpringHibernate() throws Exception
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		SessionFactory sessionFactory = (SessionFactory)applicationContext.getBean("sessionFactory") ; 
		Session session = sessionFactory.openSession() ; 
		System.out.println(session);
		User user = new User() ; 
		user.setName("testUser");
		session.save(user) ; 
 
		String hql = "from User" ; 
		List<User> userList = session.createQuery(hql).list() ; 
		for(User user1 : userList)
		{
			System.out.println(user1.getId());
			System.out.println(user1.getName());
		}
	}
	
	@org.junit.Test
	public void testTransaction() 
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		IUserService userServiceImpl = (IUserService)applicationContext.getBean("userServiceImpl") ; 
		User user = new User() ; 
		user.setName("tsetTransaction");
		userServiceImpl.add(user) ;  
	}
	
	@org.junit.Test
	public void test() throws Exception
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		ICompetitionDao competitionDaoImpl = (ICompetitionDao)applicationContext.getBean("competitionDaoImpl") ; 
		ICompetitionService competitionServiceImpl = (ICompetitionService)applicationContext.getBean("competitionServiceImpl") ; 
		//List<Competition> list = competitionServiceImpl.list() ; 
		Competition competition = competitionServiceImpl.findById(18) ; 
		System.out.println(competition.getBeginTime());
	}
	
	@org.junit.Test
	public void testLoad() throws Exception
	{
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml") ; 
		SessionFactory sessionFactory = (SessionFactory)applicationContext.getBean("sessionFactory") ; 
		Session session = sessionFactory.openSession() ; 
		Competition competition = (Competition)session.load(Competition.class, (long)18) ; 
		System.out.println(competition );
	}
	
}
