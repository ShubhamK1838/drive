package entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;


@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId; 
	private String name; 
	private String email; 
	private String password;
	private boolean verified;
	private String gender; 
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="imageid")
	private UserProfile image;

	
	public User() {

//		userId=new java.util.Random().nextInt(1000,10000);
//
	}

	public User(int userId, String name, String email, String password, String gender, UserProfile image) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.image = image;
	}

	
	public UserProfile getImage() {
		return image;
	}


	public void setImage(UserProfile image) {
		this.image = image;
	}


	

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password + ", gender="
				+ gender + ", image=" + null + "]";
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	
	
	
	

}
