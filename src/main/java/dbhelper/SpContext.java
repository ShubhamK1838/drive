package dbhelper;


import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpContext {
	
	public static
		org.springframework.context.ApplicationContext context=
			new ClassPathXmlApplicationContext("/dbhelper/springconfig.xml"); 
	
}
