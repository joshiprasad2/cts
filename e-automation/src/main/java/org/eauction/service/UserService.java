package org.eauction.service;

import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.eauction.entity.UserEntity;
import org.eauction.exceptions.EAutionGenericException;
import org.eauction.repos.UserRepository;
import org.eauction.security.UserPojo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsManager{

	private static final Logger log = LoggerFactory.getLogger(UserService.class) ;
	
	@Autowired
	private UserRepository userRepo ;
	
	@Autowired
	PasswordEncoder encoder ;
	
	public UserPojo registerUser(UserPojo user) {
		try {
			log.debug("New User Registry Invoked.");
			user.setPassword(encoder.encode(user.getPassword()));
			UserEntity entity = new UserEntity(0, user.getUsername(), user.getPassword(), null) ;
			entity = userRepo.save(entity) ;
			UserPojo userInfo = new UserPojo() ;
			userInfo.setUserId(entity.getUserID());
			userInfo.setUserName(entity.getUserName());
			userInfo.setPassword(entity.getPassword());
			
			return userInfo ;
		}catch(EAutionGenericException ex) {
			log.error("User Couldn't be Registered.");
			ex.printStackTrace();
		}
		
		return null ;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		try {
			log.info("Loading UserDetails for Username : "+username);
			Optional<UserEntity> userEntity = userRepo.findByUsername(username) ;
			return new User(userEntity.get().getUserName(),userEntity.get().getPassword(),Collections.EMPTY_LIST) ;
		}catch(NoSuchElementException ex) {
			log.error("User Not Available.");
			throw new EAutionGenericException("User Not Found.",ex) ;
		}
		catch(EAutionGenericException ex) {
			log.error("User Couldn't be Registered.");
			throw new EAutionGenericException("User Not Found.",ex) ;
		}
	}
	
	@Override
	public void createUser(UserDetails user) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateUser(UserDetails user) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteUser(String username) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void changePassword(String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean userExists(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	
	public UserPojo getUser(int userId) {
		UserEntity entity = userRepo.findById(userId).orElseThrow() ;
		UserPojo user = new UserPojo(userId, entity.getUserName(), null, null, null) ;
		return user ;
	}
}
