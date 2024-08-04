package myutil;

import java.io.*;
import java.nio.file.Paths;
import database.FileDao;
import dbhelper.SpContext;
import entities.User;

public class Storage {

    public static String path; 
    private String dir;
    private entities.File userFile;

    
    public Storage(entities.File userFile) {
        this.userFile = userFile;
       
        dir = path + File.separator + "storage" + File.separator + "d" + userFile.getUser().getUserId();
        createDirectoryIfNotExists();
    }

    public Storage(int fileId) {
        FileDao fileDao = SpContext.context.getBean("fileDao", FileDao.class);
        userFile = fileDao.getFile(fileId);
        dir = path + File.separator + "storage" + File.separator + "d" + userFile.getUser().getUserId();
        createDirectoryIfNotExists();
    }

   
    private void createDirectoryIfNotExists() {
        File file = new File(dir);
        if (!file.exists()) {
        	file.mkdirs(); 
        }
    }

    
    public File getIoFile() {
        return new File(dir + File.separator + userFile.getFileName());
    }

    
    public FileInputStream getFileInputStream() {
        try {
            return new FileInputStream(getIoFile());
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        return null;
    }

    
    public void fileSave(InputStream ist) {
        File file = getIoFile();
        try (FileOutputStream out = new FileOutputStream(file);
             InputStream in = ist) {

            if (!file.exists()) {
                file.createNewFile();
            }

            byte[] data = new byte[1024 * 4];
            int bytesRead;
            while ((bytesRead = in.read(data)) != -1) {
                out.write(data, 0, bytesRead);
            }

            FileDao fileDao = SpContext.context.getBean("fileDao", FileDao.class);
            fileDao.add(userFile);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    
    public void fileSave(byte[] fileData) {
        File file = getIoFile();
        try (FileOutputStream flo = new FileOutputStream(file)) {

            if (!file.exists()) {
                file.createNewFile();
            }

            flo.write(fileData);

            FileDao fileDao = SpContext.context.getBean("fileDao", FileDao.class);
            fileDao.add(userFile);
           

        } catch (Exception e) {
            System.out.println(e);
        }
    }

   
    public void fileDelete() {
        File file = getIoFile();
       
        if (file.exists()) {
            FileDao fileDao = SpContext.context.getBean("fileDao", FileDao.class);
            fileDao.delete(userFile);
            file.delete(); 
            System.out.println(" For FIle delete"); 
            
        }
    }

    
    public void share(User user) {
        System.out.println("Inside the share");
        entities.File rec = new entities.File(userFile, user);
        Storage st = new Storage(rec);
        st.fileSave(getFileData());
    }

    
    public byte[] getFileData() {
        File file = getIoFile();
        try (FileInputStream fis = new FileInputStream(file)) {
            return fis.readAllBytes();
        } catch (Exception e) {
            System.out.print(e);
            return null;
        }
    }
}
