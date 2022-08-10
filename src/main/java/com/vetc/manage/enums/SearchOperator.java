package com.vetc.manage.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author HungVM
 */
@Getter
@AllArgsConstructor
public enum SearchOperator {
	AND("and", "and"), OR("or", "or"), EQUALS("equals", "="), NEQ("neq", "!="), GT("gt", ">"),
	GTE("gte", ">="), LT("lt", "<"), LTE("lte", "<="), IN("in", "in"),
	not_in("not_in", "not in"), LIKE("like", "like");
	private String code;
	private String symbol;
}
