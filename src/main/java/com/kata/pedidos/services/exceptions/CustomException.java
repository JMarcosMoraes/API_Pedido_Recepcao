package com.kata.pedidos.services.exceptions;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 7343201109652899178L;
	

	public CustomException(String message, Throwable cause) {
		super(message, cause);
		 
	}
	
    public CustomException(String message) {
        super(message);
    }

}
