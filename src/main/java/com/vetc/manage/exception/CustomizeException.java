package com.vetc.manage.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
/** 
 * @Author HaNQ
 */
@Getter
@Setter
public class CustomizeException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	protected String code;
	protected String error;
	protected String message;
	protected HttpStatus httpStatus = HttpStatus.OK;

	public CustomizeException() {
	}
	
	public CustomizeException(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public CustomizeException(String code, String message,String error,HttpStatus httpStatus) {
		this.message = message;
		this.code = code;
		this.error= error;
		this.httpStatus = httpStatus;
	}
	public CustomizeException(String code, String message,HttpStatus httpStatus) {
		this.message = message;
		this.code = code;
		this.httpStatus = httpStatus;
	}
	
	public CustomizeException(String message) {
		this.message = message;
	}

	public CustomizeException(String code, String message) {
		this.message = message;
		this.code = code;
	}

	public CustomizeException(String message, Exception ex) {
		this.message = message + "\n" + ex.getMessage();

	}

	public CustomizeException(Exception ex) {
		super(ex);
	}

	public CustomizeException(String message, CustomizeException ex) {
		this.message = message + "\n" + ex.getMessage();
	}

	@Override
	public String getMessage() {
		return message;
	}
}
