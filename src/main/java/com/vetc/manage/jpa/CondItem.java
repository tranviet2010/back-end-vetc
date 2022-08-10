package com.vetc.manage.jpa;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/** 
 * @Author HungVM
 */
@ToString
@Setter
@Getter
@NoArgsConstructor
public class CondItem {
	private String attributeName;
	private String operator;
	private Object value;
	private List<CondItem> lsAndCondItem;
	private List<CondItem> lsORCondItem;

	public void addAndCondItem(CondItem item) {
		if (lsAndCondItem == null)
			lsAndCondItem = new ArrayList<CondItem>();
		lsAndCondItem.add(item);
	}

	public void addOrCondItem(CondItem item) {
		if (lsORCondItem == null)
			lsORCondItem = new ArrayList<CondItem>();
		lsORCondItem.add(item);
	}

	public CondItem(String attributeName, Object value) {
		this.attributeName = attributeName;
		this.value = value;
	}

	public CondItem(String attributeName, String operator, Object value) {
		this.attributeName = attributeName;
		this.operator = operator;
		this.value = value;
	}
}
