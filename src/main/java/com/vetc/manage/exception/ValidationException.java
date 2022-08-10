package com.vetc.manage.exception;

import org.springframework.http.HttpStatus;
/** 
 * @Author HaNQ
 */
public class ValidationException extends CustomizeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4927527204617337043L;

	public ValidationException()
	{
		super();
	}
	
	public ValidationException(long code, String message)
	{
		super(String.valueOf(code), message,HttpStatus.BAD_REQUEST);
	}
	
	public ValidationException(String code, String message)
	{
		super(code, message,HttpStatus.BAD_REQUEST);
	}
	
}
