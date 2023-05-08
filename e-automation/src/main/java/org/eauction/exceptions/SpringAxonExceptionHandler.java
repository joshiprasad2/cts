package org.eauction.exceptions;

import org.axonframework.commandhandling.CommandExecutionException;
import org.axonframework.messaging.interceptors.ExceptionHandler;
import org.axonframework.queryhandling.QueryExecutionException;
import org.eauction.model.AuctionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class SpringAxonExceptionHandler extends ResponseEntityExceptionHandler{

	
	@ExceptionHandler
	public ResponseEntity<Object> handleServiceException(final EAutionGenericException ex, final WebRequest request){
		log.error("Returning 404 : {}: {}"+ex.getMessage());
		AuctionResponse response = new AuctionResponse();  
		response.setMessage(ex.getMessage());
		response.setResponse(ex);
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(response,response.getStatus());
	}
	
	@ExceptionHandler
	public ResponseEntity<Object> handleCommandExecutionException(final CommandExecutionException  ex, final WebRequest request){
		log.error("Returning 500 : {}: {}"+ex.getMessage());
		AuctionResponse response = new AuctionResponse();  
		response.setMessage(ex.getMessage());
		response.setResponse(ex.getDetails().get());
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(response,response.getStatus());
	}
	
	@ExceptionHandler
	public ResponseEntity<Object> handleQueryExecutionException(final QueryExecutionException  ex, final WebRequest request){
		log.error("Returning 500 : {}: {}"+ex.getMessage());
		AuctionResponse response = new AuctionResponse();  
		response.setMessage(ex.getMessage());
		response.setResponse(ex.getDetails().get());
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<>(response,response.getStatus());
	}
}
