package com.vetc.manage.exception;

import com.vetc.manage.controller.dto.ResponseDto;
import com.vetc.manage.entity.ErrorList;
import com.vetc.manage.enums.ExceptionEnum;
import com.vetc.manage.enums.ResponseCode;
import com.vetc.manage.service.common.CachingService;
import com.vetc.manage.utils.JsonUtil;
import java.sql.SQLSyntaxErrorException;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class CustomizeExceptionResolver {

  private final String lang = "VI";

  @Autowired
  CachingService cachingService;

  public String buildResponseOk() {
    return buildResponseWithCode(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMessage(),
        ResponseCode.SUCCESS.getDescription());

  }

  public String buildResponseWithCode(long code, String error, String message) {
    ErrorList errorList = cachingService.getErrorList(message);
    if (!Objects.isNull(errorList)) {
      error = errorList.getErrorcode();
      message = errorList.getViMessage();
    }
    return JsonUtil.serializeObject(
        ResponseDto.builder()
            .message(message != null ? message : ResponseCode.BUSINESS_ERROR.getMessage())
            .error(error != null ? error : ResponseCode.BUSINESS_ERROR.getDescription()).build());
  }

  public String buildResponseWithCode(String error, String message) {
    ErrorList errorList = cachingService.getErrorList(message);
    if (!Objects.isNull(errorList)) {
      error = errorList.getErrorcode();
      message = errorList.getViMessage();
    }
    return JsonUtil.serializeObject(
        ResponseDto.builder()
            .message(message != null ? message : ResponseCode.BUSINESS_ERROR.getMessage())
            .error(error != null ? error : ResponseCode.BUSINESS_ERROR.getDescription()).build());
  }

  public String buildResponseWithCode(String code, String error, String message) {
    return JsonUtil.serializeObject(
        ResponseDto.builder()
            .message(message != null ? message : ResponseCode.BUSINESS_ERROR.getMessage())
            .error(error != null ? error : ResponseCode.BUSINESS_ERROR.getDescription()).build());
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex,
      WebRequest request) {
    ResponseDto error = null;
    error = ResponseDto.builder().message(ex.getConstraintName() + ": " + ex.getLocalizedMessage())
        .error(ExceptionEnum.DATA_INVALID.getCode()).build();

    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleMethodArgument(MethodArgumentNotValidException ex,
      WebRequest request) {
    ResponseDto error = null;
    for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
      error = ResponseDto.builder()
          .message(fieldError.getField() + " " + fieldError.getDefaultMessage()).error(
              ExceptionEnum.DATA_INVALID.getCode()).build();
    }
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<?> handleInvalidFormatException(HttpMessageNotReadableException ex,
      WebRequest request) {
    ResponseDto error = ResponseDto.builder()
        .message(ex.getCause().getMessage()).error(
            ExceptionEnum.DATA_INVALID.getCode()).build();

    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

//  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//  public ResponseEntity<?> handleMethodNotSupported(Exception ex, WebRequest request) {
//    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(buildResponseWithCode(
//        HttpStatus.METHOD_NOT_ALLOWED.value(), HttpStatus.METHOD_NOT_ALLOWED.toString(),
//        ex.getMessage()));
//  }


  @ExceptionHandler(ErrorListException.class)
  public ResponseEntity<?> handleExceptionErrorList(Exception ex, WebRequest request) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(buildResponseWithCode(ExceptionEnum.DATA_INVALID.getCode(), ex.getMessage()));
  }

  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public ResponseEntity<?> handleMediaTypeNotSupportd(Exception ex, WebRequest request) {
    return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
        .body(buildResponseWithCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
            HttpStatus.UNSUPPORTED_MEDIA_TYPE.toString(), ex.getMessage()));
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<?> handleAuthenticationException(Exception ex, WebRequest request) {
    return ResponseEntity.status(HttpStatus.NON_AUTHORITATIVE_INFORMATION)
        .body(buildResponseWithCode(HttpStatus.NON_AUTHORITATIVE_INFORMATION.value(),
            HttpStatus.NON_AUTHORITATIVE_INFORMATION.toString(), ex.getMessage()));
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<?> handleDataIntegrityViolationException(Exception ex, WebRequest request) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(buildResponseWithCode(HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.toString(), getFullCauseOfException(ex, "")));
  }

  @ExceptionHandler(SQLSyntaxErrorException.class)
  public ResponseEntity<?> handleSQLSyntaxErrorException(Exception ex, WebRequest request) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(buildResponseWithCode(HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.toString(), ex.getMessage()));
  }

  @ExceptionHandler(SQLGrammarException.class)
  public ResponseEntity<?> handleSQLGrammarException(Exception ex, WebRequest request) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(buildResponseWithCode(HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.toString(), ex.getMessage()));
  }

  @ExceptionHandler(InvalidDataAccessResourceUsageException.class)
  public ResponseEntity<?> handleInvalidDataAccessResourceUsageException(Exception ex,
      WebRequest request) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(buildResponseWithCode(HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.toString(), ex.getMessage()));
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ResponseEntity<?> handleConstraintViolation(Exception ex, WebRequest request) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(buildResponseWithCode(HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.toString(), ex.getMessage()));
  }

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<?> handleValidationException(Exception ex, WebRequest request) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(buildResponseWithCode(HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.toString(), ex.getMessage()));
  }

//  @ExceptionHandler(MethodArgumentNotValidException.class)
//  public ResponseEntity<?> handleMethodArgument(Exception ex, WebRequest request) {
//    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//        .body(buildResponseWithCode(HttpStatus.BAD_REQUEST.value(),
//            HttpStatus.BAD_REQUEST.toString(), ex.getMessage()));
//  }

  @ExceptionHandler(CustomizeException.class)
  protected ResponseEntity<?> handleCustomizeException(Exception ex, WebRequest request) {
    if (ex == null || request == null) {
      log.error("Exception == null || WebRequest == null");
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception is null");
    }
    CustomizeException cusExcep = (CustomizeException) ex;
    String message = cusExcep.getMessage();
    String error = cusExcep.getError();
    String errorCode = cusExcep.getCode();
    HttpStatus status = cusExcep.httpStatus;
    try {
      String errorMessage = message;
      if (!StringUtils.isEmpty(errorMessage)) {
        message = errorMessage;
      }
    } catch (Exception e) {
      // TODO: handle exception
    }

    log.error("[Exception] {} - [status] {} - [clientIP] {} - [error] {}", ex.getClass().getName(),
        status, "",
        message + ", details:" + ExceptionUtils.getStackTrace(ex));
    return ResponseEntity.status(status).body(buildResponseWithCode(errorCode, error, message));
  }

  private String getFullCauseOfException(Throwable e, String finalString) {
    if (Objects.isNull(e)) {
      return "";
    }
    finalString = e.getMessage();
    if (e.getCause() != null) {
      finalString += "; " + getFullCauseOfException(e.getCause(), finalString);
    }
    return finalString;
  }

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<?> handleError(Exception ex, WebRequest request) {
    if (ex == null || request == null) {
      log.error("Exception == null || WebRequest == null");
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Exception is null");
    }
    String message = "";
    message = getFullCauseOfException(ex, message);
    int status = HttpStatus.INTERNAL_SERVER_ERROR.value();

    log.error("[Exception] {} - [status] {} - [clientIP] {} - [error] {}", ex.getClass().getName(),
        status, "",
        message + ", details:" + ExceptionUtils.getStackTrace(ex));

    return ResponseEntity.status(status)
        .body(buildResponseWithCode(status, HttpStatus.INTERNAL_SERVER_ERROR.toString(), message));
  }

}
