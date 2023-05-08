package org.eauction.model;

import org.springframework.http.HttpStatus;


public class AuctionResponse {

	private String message ;
	private Object response ;
	private HttpStatus status ;
	private boolean isError;
	
	
	public boolean isError() {
		return isError;
	}
	public void setError(boolean isError) {
		this.isError = isError;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
	
	@Override
	public String toString() {
		return "AutionResponse [message=" + message + ", response=" + response + "]";
	}
	
	
}
