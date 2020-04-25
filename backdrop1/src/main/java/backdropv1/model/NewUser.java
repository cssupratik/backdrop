package backdropv1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userinfo")
public class NewUser {
	@Column(name = "firstname", length = 300)
	private String firstName;
	@Column(name = "lastname", length = 300)
	private String lastName;
	@Column(name = "username", unique = true, nullable = false, length = 512)
	private String userName;
	@Column(name = "passwd", length = 512 )
	private String passWord;
	@Id
	@Column(name = "emailid", unique = true, nullable = false, length = 512)
	private String emailid;
	
	public NewUser() {
		super();
	}
	public NewUser(String firstName, String lastName, String userName, String passWord, String emailid) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.passWord = passWord;
		this.emailid = emailid;
	}

	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	
}
