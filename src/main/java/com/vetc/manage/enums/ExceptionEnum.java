package com.vetc.manage.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author HungVM
 */
@Getter
@AllArgsConstructor
public enum ExceptionEnum {
EXISTED_RECORD_REFERANCE("EXISTED_RECORD_REFERANCE", "EXISTED_RECORD_REFERANCE")
	,	RECORD_NOT_FOUND("RECORD_NOT_FOUND", "record not found")
	, NOT_SUBCRIPTION("no subscription","not have subscription")
	, REQUEST_BODY_MISSING("request_body_missing","request body missing")
	, EXISTED_RECORD("EXISTED_RECORD", "Existed reccord")
	, CONSTRAINT_EXCEPTION("CONSTRAINT_EXCEPTION", "you need to clear the zone first")
	, DATA_INVALID("DATA_INVALID", "Data invalid.!");

	private String code;
	private String message;
}
