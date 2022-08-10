package com.vetc.manage.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author HungVM
 */
@Getter
@AllArgsConstructor
public enum ApiGroup {
	ALL("ALL", "/**"), NOTOKEN_RestAPI_SIT("NOTOKEN_RestAPI_SIT","/NOTOKEN_RestAPI_SIT/**");
	private String groupName;
	private String groupPath;
}
