package com.vetc.manage.controller.dto;

import java.util.List;
import org.springframework.stereotype.Service;
/** 
 * @Author HungVM
 */
@Service
public class ResponseParse {
	public ListResult buildResponse(List<?> data, int totalCount, int pSize, int pIndex){
		ListResult r = new ListResult();
		r.setData(data);
		r.setObject("list");
		r.setPageIndex(pIndex);
		r.setPageSize(pSize);
		r.setTotalCount(totalCount);
		return r;
	}
}
