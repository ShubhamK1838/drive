package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date; 


@Entity
public class File {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int fileId; 
	private String fileName;
	@Temporal(value = TemporalType.DATE)
	private Date addedDate; 
	private String size; 
	private String fileType;

	@ManyToOne
	@JoinColumn(name="userid")
	private User user; 
	
	
	public File() {
		super();
	
	}
	public File(File file, User user)
	{
		fileName=file.fileName ;
		addedDate=new Date(); 
		size=file.getSize();
		fileType=file.getFileType();
		this.user=user; 
		
	}
	public File(int fileId, String fileName, Date addedDate, String size, String fileType) {
		super();
		this.fileId = fileId;
		this.fileName = fileName;
		this.addedDate = addedDate;
		this.size = size;
		this.fileType = fileType;
	
	}
	public int getFileId() {
		return fileId;
	}
	public void setFileId(int fileId) {
		this.fileId = fileId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Date getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "File [fileId=" + fileId + ", fileName=" + fileName + ", addedDate=" + addedDate + ", size=" + size
				+ ", fileType=" + fileType + ", user=" + user + "]";
	} 
	
	
	
}
