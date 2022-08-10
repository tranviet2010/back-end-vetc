package com.vetc.manage.controller.request;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchBase implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<SearchBo> lsSearchBo;
	private SortBo sortBo;
	private int pageNo;
	private int limit;

	public Boolean isAsc() {
		if (sortBo != null)
			return sortBo.isAscOrder();
		return true;
	}

	public void addSearchCondition(SearchBo search) {
		if (lsSearchBo == null)
			lsSearchBo = new ArrayList<SearchBo>();
		lsSearchBo.add(search);
	}

	public String orderByColumns() {
		if (sortBo == null)
			return null;
		return sortBo.getFieldName();
	}

	public int getPage() {
		if (pageNo < 0)
			return 0;
		return pageNo;
	}

	public int firstItemIndex() {
		int startPage = 0;
		if (pageNo <= 0)
			startPage = 0;
		else
			startPage = pageNo - 1;
		return startPage * limit;
	}

	public int maxItemIndex() {
		int startPage = 0;
		if (pageNo <= 0)
			startPage = 1;
		else
			startPage = pageNo;
		return startPage * limit;
	}

	@Override
	public String toString() {
		return "SearchBase [lsSearchBo=" + lsSearchBo + ", sortBo=" + sortBo + ", pageNo=" + pageNo + ", limit=" + limit
				+ "]";
	}
}
