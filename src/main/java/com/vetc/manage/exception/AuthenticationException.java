package com.vetc.manage.exception;

import org.springframework.http.HttpStatus;

/**
 * @Author HungVM
 */
public class AuthenticationException extends CustomizeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4927527204617337043L;

	public AuthenticationException()
	{
		super();
	}
	
	public AuthenticationException(String code, String message)
	{
		super(code, message,HttpStatus.FORBIDDEN);
	}
	
}
