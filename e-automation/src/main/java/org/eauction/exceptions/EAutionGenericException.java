package org.eauction.exceptions;

public class EAutionGenericException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5542856336576674257L;
	
	public EAutionGenericException(String errorMessage, Object errorObj) {
		super(errorMessage);
	}

}
