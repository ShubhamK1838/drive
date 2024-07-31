package entities;

import javax.transaction.Transaction;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.transaction.jta.platform.internal.WebSphereExtendedJtaPlatform.TransactionManagerAdapter.TransactionAdapter;

import com.mysql.cj.Session;

import database.FactoryProvider;
import database.UserDao;
import dbhelper.SpContext;

public class Tester {
	
	public static void main(String ar[])
	{
		
		FactoryProvider fp=new FactoryProvider(); 
		

		
		org.hibernate.Session session=fp.getSession(); 

		
		fp.close();
		
	}

}
