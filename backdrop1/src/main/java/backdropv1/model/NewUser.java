package backdropv1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "userinfo")
@ApiModel(description = "New User Registration Model")
public class NewUser {
	@Column(name = "firstname", length = 300)
	@ApiModelProperty(notes = "The first name of the User, max char allowed : 300")
	private String firstName;
	@Column(name = "lastname", length = 300)
	@ApiModelProperty(notes = "The last name of the User, max char allowed : 300")
	private String lastName;
	@Column(name = "username", unique = true, nullable = false, length = 512)
	@ApiModelProperty(notes = "Username, must be unique value and max char allowed : 512")
	private String userName;
	@Column(name = "passwd", length = 512 )
	@ApiModelProperty(notes = "Password, should be at least 8 characters")
	private String passWord;
	@Id
	@Column(name = "emailid", unique = true, nullable = false, length = 512)
	@ApiModelProperty(notes = "Email id of the User, must be unique value and max char allowed : 512")
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
