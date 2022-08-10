package com.vetc.manage.jpa;

import com.vetc.manage.exception.ValidationException;
import java.beans.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.StringUtils;

/**
 * @Author HungVM
 */
@AllArgsConstructor
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class Condition implements Serializable {

  private Date fromDate;
  private Date toDate;

  private String orderByColumns;
  private Boolean ascOrder = true;
  private Integer firstItemIndex = 0;
  private Integer maxItems = 0;
  private Boolean exactMatch = true;
  private Map<String, Object> params = new HashMap<>();
  private Map<String, String> paramsOperator = new HashMap<>();
  private List<CondItem> lsCondItem = new ArrayList<CondItem>();

  public void addCondItem(CondItem item) {
    lsCondItem.add(item);
  }

  public List<CondItem> getCondItems() {
    return lsCondItem;
  }


  public Condition() {
  }

  public Condition(String orderByColumns, Boolean ascOrder, Integer firstItemIndex,
      Integer maxItems,
      Boolean exactMatch) {
    super();
    this.orderByColumns = orderByColumns;
    this.ascOrder = (ascOrder == null) ? true : ascOrder;
    this.firstItemIndex = Optional.ofNullable(firstItemIndex).orElse(0);
    this.maxItems = (maxItems == null) ? 0 : maxItems;
    this.exactMatch = (exactMatch == null) ? true : exactMatch;
  }

  public void reSetCondition(String orderByColumns, Boolean ascOrder, Integer firstItemIndex,
      Integer maxItems,
      Boolean exactMatch) {
    if (orderByColumns != null) {
      this.setOrderByColumns(orderByColumns);
    }
    if (ascOrder != null) {
      this.setAscOrder(ascOrder);
    }
    if (firstItemIndex != null) {
      this.setFirstItemIndex(firstItemIndex);
    }
    if (maxItems != null) {
      this.setMaxItems(maxItems);
    }
    if (exactMatch != null) {
      this.setExactMatch(exactMatch);
    }
  }

  public Date getFromDate() {
    if (containsKey("from_date")) {
      fromDate = getDateValue("from_date");
    }
    return fromDate;
  }

  public Date getToDate() {
    if (containsKey("to_date")) {
      toDate = getDateValue("to_date");
    }
    return toDate;
  }

  public String getOrderByColumns() {
    if (containsKey("order_columns")) {
      orderByColumns = getStringValue("order_columns");
    }
    return orderByColumns;
  }

  public Boolean isAscOrder() {
    if (ascOrder == null) {
      ascOrder = getBooleanValue("asc_order");
    }
    return ascOrder;
  }

  public Integer getFirstItemIndex() {
    if (containsKey("first_index")) {
      firstItemIndex = getIntValue("first_index");
    }
    return firstItemIndex;
  }

  public Integer getMaxItems() {
    if (containsKey("max_items")) {
      maxItems = getIntValue("max_items");
    }
    return maxItems;
  }

  public Boolean isExactMatch() {
    if (containsKey("exact_match")) {
      exactMatch = getBooleanValue("exact_match");
    }
    return exactMatch;
  }

  public void setParams(Map<String, Object> params) {
    if (params != null) {
      this.params = params;
    }
  }

  private boolean containsKey(String key) {
    return params.containsKey(key);
  }

  public Object get(String key) {
    return params.get(key);
  }

  public void add(String key, Object value) {
    if (params != null && !StringUtils.isEmpty(value)) {
      params.put(key, value);
    }
  }

  public void add(String key, String operator, Object value) {
    if (!StringUtils.isEmpty(value)) {
      params.put(key, value);
      paramsOperator.put(key, operator);
    }
  }

  public String getOperator(String key) {
    if (paramsOperator != null && paramsOperator.get(key) != null) {
      return paramsOperator.get(key);
    }
    return null;
  }

  private Integer getIntValue(String key) {
    if (params != null && params.get(key) != null) {
      return Integer.valueOf(params.get(key).toString());
    }
    return null;
  }

  @Transient
  public Long getLongValue(String key) {
    if (params != null && params.get(key) != null) {
      return Long.valueOf(params.get(key).toString());
    }
    return null;
  }

  @Transient
  public Double getDoubleValue(String key) {
    if (params != null && params.get(key) != null) {
      return Double.valueOf(params.get(key).toString());
    }
    return null;
  }

  @Transient
  public Float getFloatValue(String key) {
    if (params != null && params.get(key) != null) {
      return Float.valueOf(params.get(key).toString());
    }
    return null;
  }

  @Transient
  public String getStringValue(String key) {
    if (params != null && params.get(key) != null) {
      return (params.get(key) != null) ? params.get(key).toString().trim() : null;
    }
    return null;
  }

  @Transient
  private Date getDateValue(String key) {
    if (params != null && params.get(key) != null) {
      return (Date) params.get(key);
    }
    return null;
  }

  @Transient
  private Boolean getBooleanValue(String key) {
    return params != null && containsKey(key) && ("1".equals(getStringValue(key))
        || "true".equals(getStringValue(key)) || "TRUE".equals(getStringValue(key)));
  }
  /////////////////////////////////////////////////////////

  public Integer getIntValue(String key, Boolean required) throws ValidationException {
    if (params != null && params.get(key) != null) {
      return Integer.valueOf(params.get(key).toString());
    } else {
      throw new ValidationException(10001, "validate key required " + key);
    }
  }

  @Transient
  public Long getLongValue(String key, Boolean required) throws ValidationException {
    if (params != null && params.get(key) != null) {
      return Long.valueOf(params.get(key).toString());
    } else {
      throw new ValidationException(10001, "validate key required " + key);
    }
  }

  @Transient
  public Double getDoubleValue(String key, Boolean required) throws ValidationException {
    if (params != null && params.get(key) != null) {
      return Double.valueOf(params.get(key).toString());
    } else {
      throw new ValidationException(10001, "validate key required " + key);
    }
  }

  @Transient
  public Float getFloatValue(String key, Boolean required) throws ValidationException {
    if (params != null && params.get(key) != null) {
      return Float.valueOf(params.get(key).toString());
    } else {
      throw new ValidationException(10001, "validate key required " + key);
    }
  }

  @Transient
  public String getStringValue(String key, Boolean required) throws ValidationException {
    if (params != null && params.get(key) != null) {
      return (params.get(key) != null) ? params.get(key).toString().trim() : null;
    } else {
      throw new ValidationException(10001, "validate key required " + key);
    }
  }

  @Transient
  public Date getDateValue(String key, Boolean required) throws ValidationException {
    if (params != null && params.get(key) != null) {
      return (Date) params.get(key);
    } else {
      throw new ValidationException(10001, "validate key required " + key);
    }
  }

  @Transient
  public Boolean getBooleanValue(String key, Boolean required) throws ValidationException {
    return params != null && containsKey(key) && ("1".equals(getStringValue(key))
        || "true".equals(getStringValue(key)) || "TRUE".equals(getStringValue(key)));

  }

}
