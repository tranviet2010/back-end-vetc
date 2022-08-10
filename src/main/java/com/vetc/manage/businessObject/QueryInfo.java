package com.vetc.manage.businessObject;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QueryInfo {
	public String queryFilter;
	public int pageSize;
	public int pageIndex;
}
