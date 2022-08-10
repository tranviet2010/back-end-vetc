package com.vetc.manage.controller.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * @Author HungVM
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ListResult<T> {
	protected String object = "list";
	protected int totalCount;
	protected int pageSize;
	protected int pageIndex;
	protected List<T> data;


	public ListResult(List<T> data) {
		this.data = data;
		this.totalCount = this.data.size();
	}
}
