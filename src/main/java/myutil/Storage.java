package myutil;

import java.io.*;
import java.util.zip.InflaterInputStream;

import database.FileDao;
import dbhelper.SpContext;
import entities.User;

public class Storage {

	private String dir;

	private entities.File userFile;

	public Storage(entities.File userFile) {
		this.userFile = userFile;
		dir = ("C:\\Users\\shubh\\git\\Drive\\src\\main\\webapp\\storage\\d" + userFile.getUser().getUserId());
		try {

			File file = new File(dir);
			if (!file.exists()) {
				file.mkdir();
			}
		} catch (Exception e) {
			System.out.print(e);
		}
	}
	
	public File getIoFile()
	{
		return new File(dir+"\\"+userFile.getFileName()); 
	}
	
	public FileInputStream getFileInputStream()
	{
		
		try {
			FileInputStream ios=new FileInputStream(getIoFile()) ;
			
			return ios; 
		} catch (FileNotFoundException e) {
			
			System.out.println(e);
		}
		return null; 
	}
	
	public Storage(int fileId)
	{
		FileDao fileDao = SpContext.context.getBean("fileDao", FileDao.class);
		userFile=fileDao.getFile(fileId); 
		
	}
	public FileInputStream getInputStream()
	{
		
		try {
			FileInputStream fos=new FileInputStream(dir+"\\"+userFile.getFileName());
			
			return fos; 
			
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} 
		return null; 
	}
	
	public void fileDelete()
	{
		File file=new File(dir+"\\"+userFile.getFileName()); 
		
		if(file.exists()) {
			FileDao fileDao = SpContext.context.getBean("fileDao", FileDao.class);
			file.delete(); 
			fileDao.delete(userFile);
		}
		
	}
	public void fileSave(InputStream ist) {
		try {

			File file = new File(dir + "\\" + userFile.getFileName());


			if (!file.exists())
				file.createNewFile();
			else {
				return;
			}

			byte[] data = new byte[1024 * 4];
			FileOutputStream out = new FileOutputStream(file);

			while (ist.read(data) != -1) {
				out.write(data);
		
			}
			

			
			FileDao fileDao = SpContext.context.getBean("fileDao", FileDao.class);
			fileDao.add(userFile);
			out.close(); 
			ist.close(); 
			
		} catch (Exception er) {
			System.out.println(er);
		}
	}

	public void fileSave(byte[] fileData) {
		try {

			File file = new File(dir + "\\" + userFile.getFileName());


			if (!file.exists())
				System.out.println(file.createNewFile());
			else {
				return;
			}

			FileOutputStream flo = new FileOutputStream(file);

			flo.write(fileData);

			flo.close();

			FileDao fileDao = SpContext.context.getBean("fileDao", FileDao.class);
			fileDao.add(userFile);
			System.out.println(" file added inside ");

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void share(User user) {
		System.out.println(" inside the share ");
		entities.File rec = new entities.File(userFile, user);
		Storage st = new Storage(rec);
		st.fileSave(getFileData());

	}

	public byte[] getFileData() {
		try {

			FileInputStream fos = new FileInputStream(dir + "\\" + userFile.getFileName());
			return fos.readAllBytes();
		} catch (Exception e) {

			System.out.print(e);
			return null;
		}

	}

}
