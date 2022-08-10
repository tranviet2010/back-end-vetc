package com.vetc.manage.controller.base;

import com.google.gson.Gson;
import com.vetc.manage.common.ClientInfo;
import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.controller.request.SearchBo;
import com.vetc.manage.controller.request.SortBo;
import com.vetc.manage.enums.SearchOperator;
import java.util.Enumeration;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ControllerBase {

  @Autowired
  protected HttpServletRequest request;

  @Autowired
  protected ClientInfo clientInfo;

  @Autowired
  protected Gson gson;

  public ResponseEntity<Object> responseOK(Object result) {
    return ResponseEntity.ok(result);
  }

  protected void addSearchField(SearchBase searchBase, Enumeration<String> params,
      Map<String, String> tbPathVariable) {
    if (searchBase == null) {
      searchBase = new SearchBase();
    }
    if (params != null) {
      while (params.hasMoreElements()) {
        String name = params.nextElement();
        if (name.equals("pageIndex") || name.equals("pageSize") || name.equals("order_field")
            || name.equals("asc")) {
          continue;
        }
        splitParam(searchBase, name, request.getParameter(name));
      }
    }
    if (tbPathVariable != null && tbPathVariable.size() >= 0) {
      for (String key : tbPathVariable.keySet()) {
        splitParam(searchBase, key, tbPathVariable.get(key));
      }
    }
  }

  public ListResult createNullResponse() {
    ListResult ls = new ListResult();
    ls.setObject("list");
    ls.setData(null);
    ls.setTotalCount(0);
    ls.setPageSize(20);
    ls.setPageIndex(1);
    return ls;
  }

  private SearchBo buildParam(String key, String value) {
    if (key == null || key.isEmpty()) {
      return null;
    }
    String operator = "=";
    String symbol = "=";
    String realValue = value;
    if (value.toLowerCase().contains(":")) {
      operator = value.substring(0, value.indexOf(":"));
      symbol = getOperatorSymbol(operator);
      realValue = value.substring(value.indexOf(":") + 1, value.length());
    }
    return buildSearchField(key, symbol, realValue);
  }

  private void splitParam(SearchBase searchBase, String key, String value) {
    if (key == null || key.isEmpty() || value == null || value.isEmpty()) {
      return;
    }
    String leftValue = null;
    String rightValue = null;
    String operator = "=";
    if (value.toLowerCase().contains("and")) {
      operator = "and";
      leftValue = value.split(operator)[0].trim();
      rightValue = value.split(operator)[1].trim();
    } else if (value.toLowerCase().contains("or")) {
      operator = "or";
      leftValue = value.split(operator)[0].trim();
      rightValue = value.split(operator)[1].trim();
    }

    switch (operator) {
      case "and":
        searchBase.addSearchCondition(searchAndOperator(key, leftValue, rightValue));
        break;
      case "or":
        searchBase.addSearchCondition(searchOrOperator(key, leftValue, rightValue));
        break;
      default:
        searchBase.addSearchCondition(buildParam(key, value));
    }
  }

  private SearchBo searchAndOperator(String key, String leftValue, String rightValue) {
    SearchBo leftBO = buildParam(key, leftValue);
    leftBO.addAndSearch(buildParam(key, rightValue));
    return leftBO;
  }

  private SearchBo searchOrOperator(String key, String leftValue, String rightValue) {
    SearchBo leftBO = buildParam(key, leftValue);
    leftBO.addOrSearch(buildParam(key, rightValue));
    return leftBO;
  }

  private String getOperatorSymbol(String operator) {
    try {
      if (operator == null || operator.isEmpty()) {
        return null;
      }
      SearchOperator operNum = SearchOperator.valueOf(operator.trim().toUpperCase());
      if (operNum == null) {
        return null;
      }
      return operNum.getSymbol();
    } catch (Exception e) {
      log.error(e.getLocalizedMessage());
      // TODO: handle exception
    }
    return null;
  }

  protected SearchBo buildSearchField(String name, String operator, String value) {
    SearchBo searchBo = new SearchBo();
    searchBo.setName(name);
    searchBo.setOperator(operator == null || operator.isEmpty() ? "=" : operator);
    searchBo.setValue(value);
    return searchBo;
  }

  protected void addSearchField(SearchBase searchBase, String name, String value) {
    splitParam(searchBase, name, value);
  }

  protected SearchBase buildSearchBase(Integer pageIndex, Integer pageSize, String sortField,
      Boolean isAsc) {
    SearchBase searchBase = new SearchBase();
    searchBase.setLimit(pageSize != null && pageSize > 0 ? pageSize : 20);
    searchBase.setPageNo(pageIndex != null && pageIndex >= 1 ? pageIndex - 1 : 0);
    if (sortField != null && !sortField.isEmpty()) {
      SortBo sortBo = new SortBo();
      sortBo.setFieldName(sortField);
      sortBo.setSortType(isAsc ? "asc" : "desc");
      searchBase.setSortBo(sortBo);
    }
    return searchBase;
  }
}
