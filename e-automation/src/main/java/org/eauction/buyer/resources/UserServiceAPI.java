package org.eauction.buyer.resources;

import java.util.Collections;

import javax.validation.Valid;

import org.eauction.model.AuctionResponse;
import org.eauction.security.JWTUtility;
import org.eauction.security.UserPojo;
import org.eauction.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/e-auction/api/v1/user")
public class UserServiceAPI {

	@Autowired
	private UserService userService ;
	
	@Autowired
	JWTUtility jWTUtility ;
	
	@Operation(summary = "Register New User / Buyer/ Seller",
			description = "Register New User / Buyer/ Seller",
			tags = "User-Service",
			responses = {
				@ApiResponse(
					responseCode = "200",
					description = "Ok",
					content = {
						@Content(
							mediaType = "application/json"
						)}
					)
			},
			requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
					content = {
							@Content(
								mediaType = "application/json"	
						)},
					description = "Register User", required = true))
	@PostMapping("/register")
	public ResponseEntity<AuctionResponse> registerUser(@RequestBody @Validated UserPojo userInfo,
			BindingResult result){
		AuctionResponse response = new AuctionResponse() ;
		log.info(" user info "+userInfo.getPassword()+" | "+userInfo.getUsername()+"| "+userInfo.getUserId());
		if(result.hasErrors()) {
			response.setMessage("Input Validation Failed.") ;
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}else {
			userInfo = userService.registerUser(userInfo) ;
			System.out.println(" user info "+userInfo.getPassword()+" | "+userInfo.getUsername()+"| "+userInfo.getUserId());
			Authentication auth = UsernamePasswordAuthenticationToken.authenticated(userInfo, userInfo.getPassword(), Collections.EMPTY_LIST) ;
			response.setMessage("User Created.") ;
			response.setResponse(jWTUtility.generateToken(userInfo)) ;
		}
		return ResponseEntity.ok(response) ;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<AuctionResponse> user(@PathVariable int id){
		AuctionResponse response = new AuctionResponse() ;
		UserPojo user2 = userService.getUser(id);
		response.setResponse(user2) ;
		return ResponseEntity.ok(response) ;
	}
}
