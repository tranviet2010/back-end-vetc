package com.vetc.manage.controller.request;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
/** 
 * @Author HungVM
 */
@Getter
@Setter
public class SearchBo {
	private String name;
	private String operator;
	private Object value;

	private List<SearchBo> orSearch;
	private List<SearchBo> andSearch;

	public void addAndSearch(SearchBo item) {
		if (andSearch == null)
			andSearch = new ArrayList<SearchBo>();
		andSearch.add(item);
	}

	public void addOrSearch(SearchBo item) {
		if (orSearch == null)
			orSearch = new ArrayList<SearchBo>();
		orSearch.add(item);
	}
}
