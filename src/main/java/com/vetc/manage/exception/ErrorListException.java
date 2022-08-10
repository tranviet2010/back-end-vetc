package com.vetc.manage.exception;

import org.springframework.http.HttpStatus;

/**
 * @Author HaNQ
 */
public class ErrorListException extends CustomizeException {

  /**
   *
   */
  private static final long serialVersionUID = 4927527204617337043L;

  public ErrorListException() {
    super();
  }

  public ErrorListException(long code, String message) {
    super(String.valueOf(code), message, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  public ErrorListException(String code, String message) {
    super(code, message, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  public ErrorListException(String message) {
    super(message);
  }

}
