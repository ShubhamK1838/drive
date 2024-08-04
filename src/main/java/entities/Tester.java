package entities;

import javax.transaction.Transaction;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.transaction.jta.platform.internal.WebSphereExtendedJtaPlatform.TransactionManagerAdapter.TransactionAdapter;

import com.mysql.cj.Session;

import database.FactoryProvider;
import database.FileDao;
import database.UserDao;
import dbhelper.SpContext;

public class Tester {
	
	public static void main(String ar[])
	{
		
		FileDao dao=SpContext.context.getBean("fileDao", FileDao.class); 
		
		System.out.print(dao.getUserFiles(7));
		
	}

}
