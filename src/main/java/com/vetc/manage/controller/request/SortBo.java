package com.vetc.manage.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
/** 
 * @Author HungVM
 */
@Getter
@Setter
public class SortBo {
	private String fieldName;
	private String sortType;

	public Boolean isAscOrder() {
		if (sortType != null && !sortType.isEmpty()) {
			return !sortType.equalsIgnoreCase(SortType.DESC.getCode());
		}
		return true;
	}

	@Getter
	@AllArgsConstructor
	public enum SortType {
		ASC("asc"), DESC("desc");

		private String code;
	}
}
