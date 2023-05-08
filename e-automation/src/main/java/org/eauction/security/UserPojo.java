package org.eauction.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPojo implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1682549346725255631L;
	
	private int userId ;
	private String token ;
	
	@NotNull(message = "Username Cannot Be Null")
	private String userName ;
	
	@NotNull(message = "Password Cannot Be Null")
	private String password ;
	
	
	public UserPojo(int userId, String token, @NotNull(message = "Username Cannot Be Null") String userName,
			@NotNull(message = "Password Cannot Be Null") String password,
			Collection<GrantedAuthority> grants) {
		super();
		this.userId = userId;
		this.token = token;
		this.userName = userName;
		this.password = password;
		this.grants = grants ;
	}
	
	
	public UserPojo() {
		super();
		// TODO Auto-generated constructor stub
	}


	private Collection<GrantedAuthority> grants = new ArrayList<>() ;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.EMPTY_LIST;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.userName;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
