package org.eauction.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtility {

	private static final String SECRET = "secret" ;
	public String extractUserName(String token) {
		return extractsClaims(token,Claims::getSubject) ;
	}
	
	public <T> T extractsClaims(String token, Function<Claims,T> claimResolver){
		final Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
		
		return claimResolver.apply(claims) ;
	}
	
	public Date extractExpiration(String token) {
		return extractsClaims(token,Claims::getExpiration) ;
	}
	
	
	public boolean isTokenValid(String token) {
		return extractExpiration(token).before(new Date()) ;
	}
	
	public String generateToken(UserPojo user) {
		Map<String,Object> claims = new HashMap<>() ;
		
		return creteToken(claims,user.getUsername()) ;
	}
	
	private String creteToken(Map<String,Object> claims,String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
			.signWith(SignatureAlgorithm.HS256, SECRET)
			.compact() ;
	}
	
	public boolean validateToken(String token, String userName) {
		final String username = extractUserName(token) ;
		
		return (username.equalsIgnoreCase(userName) && !isTokenValid(token)) ;
	}
	
}
