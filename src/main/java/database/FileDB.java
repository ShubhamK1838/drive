package database;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import entities.File;

public class FileDB implements FileDao {

	 private HibernateTemplate hibernateTemplate; 

	
	@Override
	@Transactional
	public void add(File file) {
		hibernateTemplate.save(file); 
	}

	@Override
	@Transactional
	public void update(File file) {
		hibernateTemplate.update(file);
	}

	@Override
	@Transactional
	public void delete(File file) {
		
		hibernateTemplate.delete(file);
	}
	@Override
	@Transactional
	public void delete(int id )
	{
		hibernateTemplate.delete(getFile(id));
	}

	@Override
	public File getFile(int file_id) {
	
		return hibernateTemplate.get(File.class, file_id); 
	}

	@Override
	public List<File> getUserFiles(int user_id) {
		DetachedCriteria cr=DetachedCriteria.forClass(File.class) ; 
		cr.add(Restrictions.eq("userid", user_id)); 
		return (List<File>)hibernateTemplate.findByCriteria(cr);
		
	}

	@Override
	public List<File> getFiles() {
		return hibernateTemplate.loadAll(File.class); 
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
}
