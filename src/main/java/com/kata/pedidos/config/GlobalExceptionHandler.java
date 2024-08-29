package com.kata.pedidos.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.kata.pedidos.services.exceptions.CustomException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = {CustomException.class})
    protected ResponseEntity<?> handleCustomException(CustomException ex, WebRequest webRequest ) {
		Map<String, Object> body = new HashMap<>();
		body.put("Mensagem", ex.getMessage());
		
        return new ResponseEntity<>(body,  HttpStatus.OK);
    }
}
