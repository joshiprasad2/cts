package org.eauction.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eauction.exceptions.EAutionGenericException;
import org.eauction.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTRequestFiler extends OncePerRequestFilter {

	private static final Logger log = LoggerFactory.getLogger(JWTRequestFiler.class) ;
	
	@Autowired
	JWTUtility jwtUtil ;
	
	@Autowired
	UserService userService ;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String authorization = request.getHeader("Authorization") ;
		String userName = null ;
		String jwt = null ;
		
		if(authorization != null && authorization.startsWith("Bearer ")) {
			jwt = authorization.substring(7);
			userName = jwtUtil.extractUserName(jwt) ;
		}
		
		log.info("Username : "+userName);
		if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			try {
				UserDetails userDet = userService.loadUserByUsername(userName) ;
				if(jwtUtil.validateToken(jwt, userDet.getUsername())) {
						log.info("Token is Valid");
						UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(userDet,null,userDet.getAuthorities()) ;
						userToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						SecurityContextHolder.getContext().setAuthentication(userToken);
				}
			}catch(EAutionGenericException exe) {
				throw new EAutionGenericException(exe.getMessage(),exe) ;
			}
		}
		filterChain.doFilter(request, response);
	}

}
