package com.vetc.manage.exception;

import org.springframework.http.HttpStatus;
/** 
 * @Author HaNQ
 */
public class SQLRepositoryException extends CustomizeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4927527204617337043L;

	public SQLRepositoryException() {
		super();
	}

	public SQLRepositoryException(String code, String message) {
		super(code, message, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
