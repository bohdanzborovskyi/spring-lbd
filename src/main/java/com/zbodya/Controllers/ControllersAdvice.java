package com.zbodya.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllersAdvice
{
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> handleDataRetrievalException(Exception ex)
	{
		return new ResponseEntity<ErrorMessage>(new ErrorMessage(ex.getMessage()),HttpStatus.BAD_REQUEST);
	}
	
	class ErrorMessage
	{
		String message;
		
		public ErrorMessage(String message) 
		{
			this.message = message;
		}
		
		public String getMessage() 
		{
			return this.message;
		}
	}
}
