package database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FactoryProvider  {
	
	private SessionFactory sessionFactory ; 
	private Session session; 
	
	public FactoryProvider()
	{
		Configuration conf=new Configuration(); 
		conf.configure("/entities/config.xml");
		sessionFactory=conf.buildSessionFactory(); 
		session=sessionFactory.openSession(); 
	}
	
	public SessionFactory getFactory()
	{
		
		
		return sessionFactory; 
	}
	public Session getSession()
	{
		return session; 
	}
	public void close()
	{
		session.close();
		sessionFactory.close(); 
	}
}
