package dbhelper;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import database.FileDao;
import database.UserDao;
import entities.File;






public class Tester {
	


	public static void main(String ar[]) {

		

		ApplicationContext context=SpContext.context; 
	 
		UserDao udao=context.getBean("userDao", UserDao.class); 
		
		FileDao dao=context.getBean("fileDao", FileDao.class); 
	
		
		File file=dao.getFile(4); 
		
		System.out.println("" + file ); 
		System.out.println(" Configuration Done ");
		
		
		System.out.println();
		
		
		
	}
}
