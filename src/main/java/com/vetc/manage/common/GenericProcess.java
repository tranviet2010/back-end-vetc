package com.vetc.manage.common;

import com.vetc.manage.businessObject.QueryInfo;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GenericProcess {

  public QueryInfo getFilterSQL(HttpServletRequest request) {
    String sqlQuery = " 1=1 ";
    int pageSize = 20;
    int pageIndex = 1;
    Set paramMaps = request.getParameterMap().keySet();
    for (Object key : paramMaps) {
      String strKey = key.toString();
      String strValue = request.getParameter(strKey);

      if (StringUtils.isAllEmpty(strKey)) {
        continue;
      }

      if (strKey.equalsIgnoreCase("PAGESIZE")) {
        pageSize = Integer.parseInt(strValue);
      }
      if (strKey.equalsIgnoreCase("PAGEINDEX")) {
        pageIndex = Integer.parseInt(strValue);
      }

      if (!strKey.equalsIgnoreCase("PAGESIZE") && !strKey.equalsIgnoreCase("PAGEINDEX")) {
        if (StringUtils.containsIgnoreCase(strKey, "DATE")) {
          strValue = buildBussinessParam(strKey, strValue, true);
          sqlQuery = sqlQuery + " AND (" + strValue + ") ";
        } else {
          strValue = buildBussinessParam(strKey, strValue, false);
          sqlQuery = sqlQuery + " AND (" + strValue + ") ";
        }
      }
    }

    QueryInfo info = new QueryInfo();
    info.pageIndex = pageIndex;
    info.pageSize = pageSize;
    info.queryFilter = sqlQuery;
    return info;
  }

  public String getFilterSQLForSE(HttpServletRequest request, String name) {
    String delimiter = "|";
    Set paramMaps = request.getParameterMap().keySet();
    log.info("filter: list strKey:" + paramMaps);
    for (Object key : paramMaps) {
      String strKey = key.toString();
      if (!StringUtils.isAllEmpty(strKey) && StringUtils.containsIgnoreCase(strKey, name)) {
        String[] arrKeypath = strKey.split(delimiter);
        for (int i = 0; i < arrKeypath.length; i++) {
          if (arrKeypath[i].equalsIgnoreCase(name)) {
            return arrKeypath[i + 1];
          }
        }
      }
    }
    return "%";
  }

  private String buildBussinessParam(String fieldName, String param, Boolean isDate) {
    try {
      if (param.length() == 0) {
        return "";
      }
      String strParam = "", strRet = "", strValue;
      String strInput = param;
      String strTempParam = " %s %s '%s' ";
      if (isDate) {
        strTempParam = " %s %s TO_DATE('%s','dd/MM/rrrr') ";
      }
      while (true) {
        try {
          if (StringUtils.containsIgnoreCase(strInput, "LTE:") || StringUtils.containsIgnoreCase(
              strInput, "LT:")
              || StringUtils.containsIgnoreCase(strInput, "GTE:") || StringUtils.containsIgnoreCase(
              strInput, "GT:")
              || StringUtils.containsIgnoreCase(strInput, "EQ:")) {
            strParam = strInput.substring(0, strInput.indexOf(" "));
          } else {
            strParam = strInput;
          }

        } catch (Exception ex) {
          log.error("buildBussinessParam:", ex);
          strParam = strInput;
        }

        if (StringUtils.containsIgnoreCase(strParam, "LTE:") || StringUtils.containsIgnoreCase(
            strParam, "LT:")
            || StringUtils.containsIgnoreCase(strParam, "GTE:") || StringUtils.containsIgnoreCase(
            strParam, "GT:")
            || StringUtils.containsIgnoreCase(strParam, "EQ:")) {
          if (strParam.substring(0, 3).equalsIgnoreCase("lte")) {
            strValue = strParam.substring(4);
            strRet = strRet + String.format(strTempParam, fieldName, "<=", strValue);
          } else if (strParam.substring(0, 3).equalsIgnoreCase("gte")) {
            strValue = strParam.substring(4);
            strRet = strRet + String.format(strTempParam, fieldName, ">=", strValue);
          } else if (strParam.substring(0, 2).equalsIgnoreCase("lt")) {
            strValue = strParam.substring(3);
            strRet = strRet + String.format(strTempParam, fieldName, "<", strValue);
          } else if (strParam.substring(0, 2).equalsIgnoreCase("gt")) {
            strValue = strParam.substring(3);
            strRet = strRet + String.format(strTempParam, fieldName, ">", strValue);
          } else if (strParam.substring(0, 2).equalsIgnoreCase("eq")) {
            strValue = strParam.substring(3);
            strRet = strRet + String.format(strTempParam, fieldName, "=", strValue);
          } else {
            strRet = strRet + String.format(strTempParam, fieldName, "=", strParam);
          }
        } else {
          strRet = strRet + String.format(strTempParam, fieldName, "=", strParam);
        }

        if (strInput.trim().length() > strParam.trim().length()) {
          strInput = strInput.substring(strParam.length()).trim();
          if (StringUtils.indexOfIgnoreCase(strInput, "AND") == 0) {
            strInput = strInput.substring(3).trim();
            strRet = strRet + " AND ";
          } else if (StringUtils.indexOfIgnoreCase(strInput, "OR") == 0) {
            strInput = strInput.substring(2).trim();
            strRet = strRet + " OR ";
          }
        } else {
          break;
        }
      }
      return strRet;
    } catch (Exception ex) {
      log.error("buildBussinessParam:", ex);
      return "";
    }
  }
}
