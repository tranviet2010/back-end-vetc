package com.vetc.manage.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author HungVM
 */
@Getter
@AllArgsConstructor
public enum ResponseCode {
	SUCCESS("", "successfull", "0"), BUSINESS_ERROR("error", "internal server error", "-1");
	private String description;
	private String message;
	private String code;
}
