package com.vetc.manage.controller.dto;

/** 
 * @Author HungVM
 */
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResultResp {
	
	private ResponseDto response;
	private Object data;

}
