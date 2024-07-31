package database;


import entities.*; 

public interface  FileDao {

	public void  add(File file); 
	public void update(File file ); 
	public void delete(File file ); 
	public File getFile(int file_id  ); 
	public java.util.List<File> getUserFiles(int user_id ); 
	public java.util.List<File> getFiles(); 
	public void delete(int id); 
}
