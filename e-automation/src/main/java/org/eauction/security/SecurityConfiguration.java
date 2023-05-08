package org.eauction.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

	
	@Autowired
	InvalidUserAuthEntryPoint userEntryPoint ;
	
	@Autowired
	JWTRequestFiler jwtFilter ;
	
	   
//	   @Bean
//	    public UserDetailsManager  userDetailsService() {
//		   
//	        UserDetails user = User
//	                .username("user")
//	                .password("password")
//	                .roles("USER")
//	                .build();
//	        return new UserDetailsManager(user);
//	    }
	   
	
	   
	   @Bean
	   protected AuthenticationManager authenticationManager() throws Exception{
		   return new CustomAuthenticationManager();
	   }
	   
	   @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		   http
		   	.csrf().disable()
		   	.cors().disable()
	         .authorizeRequests()
	            .antMatchers("/e-auction/api/v1/user/register").permitAll()
	            .antMatchers("/h2-console/**").permitAll()
	            .antMatchers("/eauction-api.html").permitAll()
	            .antMatchers("/swagger-ui/**").permitAll()
	            .antMatchers("/api-docs/**").permitAll()
	            .anyRequest().authenticated()
	            .and()
	            .exceptionHandling()
	            .authenticationEntryPoint(userEntryPoint)
	            .and()
	            .sessionManagement()
	            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		   http.headers().frameOptions().disable();
		   http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	            
//	         .formLogin()
//	            .loginPage("/login")
//	            .permitAll()
//	            .and()
//	            .logout()
//	            .permitAll();
	        return http.build();
	    }
}
