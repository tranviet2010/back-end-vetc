package com.vetc.manage.service.common;

import com.vetc.manage.entity.ApParam;
import com.vetc.manage.entity.ErrorList;
import com.vetc.manage.repository.ApParamRepository;
import com.vetc.manage.repository.ErrorListRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CachingService {

  public List<ApParam> apParams;

  public List<ErrorList> errorLists;

  public static Map<String, Object> cacheData = new HashMap<>();

  @Autowired
  private ApParamRepository apParamRepository;

  @Autowired
  private ErrorListRepository errorListRepository;


  public void setCache(String transactionId, Object data) {
    cacheData.put(transactionId, data);
  }

//  public Object getCache(String transactionId) {
//    Object data = cacheData.get(transactionId);
//    if (Objects.isNull(data)) {
//      throw new CustomizeException(ErrorCode.DATA_MISSING.getErrCode(),
//          ErrorCode.DATA_MISSING.getErrMessage(),
//          HttpStatus.BAD_REQUEST);
//    }
//    return data;
//  }

  public void clearCache(String transactionId) {
    if (!Objects.isNull(cacheData.get(transactionId))) {
      cacheData.remove(transactionId);
    }
  }

  @Scheduled(cron = "*/3 * * * *")
  @PostConstruct
  public void loadCached() {
    log.info("run job");
    apParams = apParamRepository.findAll();
    errorLists = errorListRepository.findAll();
    if (Objects.isNull(apParams)) {
      log.error("apParams is null.");
    }
  }

  public String getApParam(String group, String name) {
    if (Objects.isNull(apParams)) {
      return null;
    }
    if (!StringUtils.isNotEmpty(group) || !StringUtils.isNotEmpty(name)) {
      return null;
    }
    for (ApParam item : apParams) {
      if (item.getParGroup().equals(group)) {
        if (item.getParName().equals(name)) {
          return item.getDescription();
        }
      }
    }
    return null;
  }

  public ErrorList getErrorList(String des) {
    if (Objects.isNull(errorLists)) {
      return null;
    }
    if (!StringUtils.isNotEmpty(des)) {
      return null;
    }
    for (ErrorList item : errorLists) {
      if (item.getDescription().contains(des)) {
        return item;
      }
    }
    return null;
  }
}
