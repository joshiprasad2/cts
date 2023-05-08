package org.eauction.exceptions.advice;

import java.util.HashMap;
import java.util.Map;

import org.eauction.model.AuctionResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EAutionErrorHandler {

	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgException(MethodArgumentNotValidException exn){
		Map<String, String> errorMap = new HashMap<>() ;
		exn.getBindingResult().getFieldErrors().forEach(error -> {
			System.out.println(error.getField()+" | "+ error.getDefaultMessage());
			errorMap.put(error.getField(), error.getDefaultMessage()) ;
		});
		
		return errorMap ;
	}
	
	
	@ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleGlobalException(ConstraintViolationException cexe){
		AuctionResponse response = new AuctionResponse() ;
		response.setMessage("Validation Error. Please check,  details should be unique.");
		response.setResponse("");
     return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
