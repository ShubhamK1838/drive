package database;

import java.util.List;

import javax.persistence.spi.PersistenceUnitTransactionType;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.cj.Query;

import entities.User;

public class UserDB  implements UserDao{

	private HibernateTemplate hibernateTemplate; 
	
	@Override
	@Transactional()
	public void add(User user) {
		hibernateTemplate.save(user);
	}

	@Override
	@Transactional
	public void delete(User user) {
		hibernateTemplate.delete(user);
	}
	@Transactional
	public void delete(int id)
	{
		User user=getUser(id); 
		delete(user);
	}

	@Override
	public User getUser(int id) {
			return hibernateTemplate.get(User.class, id);    
	}

	@Override
	public User getUser(String name, String pass) {
        // Creating the criteria
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        criteria.add(Restrictions.eq("email", name));
        criteria.add(Restrictions.eq("password", pass));

        // Executing the query
        List<User> users = (List<User>) hibernateTemplate.findByCriteria(criteria);

        // Returning the first result, if found
        if (users != null && !users.isEmpty()) {
            return users.get(0);
        }

        return null;
    }

	@Override
	public List<User> getUsers() {
		 return hibernateTemplate.loadAll(User.class) ;
	}

	@Override
	@Transactional
	public void update(User user) {
			hibernateTemplate.update(user);
		
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	

	
}
