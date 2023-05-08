package org.eauction.security;

import java.util.Collections;
import java.util.Optional;

import org.eauction.entity.UserEntity;
import org.eauction.repos.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomAuthenticationManager implements AuthenticationManager{

	private static final Logger log = LoggerFactory.getLogger(CustomAuthenticationManager.class) ;

	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    	String username = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";

       Optional<UserEntity> user = userRepo.findByUsername(username);
        if (user.get() == null) {
            throw new BadCredentialsException("1000");
        }
        if (!passwordEncoder.matches(password, user.get().getPassword())) {
            throw new BadCredentialsException("1000");
        }
       
        return new UsernamePasswordAuthenticationToken(username, null, Collections.EMPTY_LIST);

    }

}
