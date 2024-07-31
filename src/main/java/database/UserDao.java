package database;

import entities.User;

public interface  UserDao {

	public void add(User user); 
	public void delete(User user); 
	public User getUser(int id); 
	public User getUser(String name, String pass); 
	public java.util.List<User> getUsers();
	public void update(User user); 
	public void delete(int id ); 

	
}
