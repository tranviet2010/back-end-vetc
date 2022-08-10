package com.vetc.manage.controller.dto;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
/** 
 * @Author HungVM
 */
@Getter
@Setter
@Builder
public class ResponseDto implements Serializable {
	private String message;
	private String error;

	@Override
	public String toString() {
		return "Response [message=" + message + "]";
	}
}
