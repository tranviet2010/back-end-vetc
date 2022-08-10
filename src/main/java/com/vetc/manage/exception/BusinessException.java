package com.vetc.manage.exception;

import org.springframework.http.HttpStatus;
/** 
 * @Author HaNQ
 */
public class BusinessException extends CustomizeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4927527204617337043L;

	public BusinessException() {
		super();
	}

	public BusinessException(String code, String message) {
		super(code, message, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
