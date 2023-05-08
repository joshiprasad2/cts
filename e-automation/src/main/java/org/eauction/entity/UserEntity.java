package org.eauction.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class UserEntity {

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private int userID;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "USER_PASSWORD")
	private String password ;
	
	@Column(name = "USER_TOKEN")
	private String userToken;

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}

	public UserEntity(int userID, String userName, String password, String userToken) {
		super();
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.userToken = userToken;
	}

	public UserEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
