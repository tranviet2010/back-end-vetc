package com.vetc.manage.service;

import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.entity.VerifyRuleConfig;
import java.util.List;

public interface VerifyRuleConfigService {

  ListResult<VerifyRuleConfig> search(SearchBase searchBase) throws Exception;

  VerifyRuleConfig get(Long id);

  VerifyRuleConfig save(VerifyRuleConfig object) throws Exception;

  VerifyRuleConfig update(VerifyRuleConfig object, Long id) throws Exception;

  VerifyRuleConfig delete(Long id) throws Exception;

  List<VerifyRuleConfig> getAll();

  List<VerifyRuleConfig> getVerifyRuleConfigByCust(Long custId);
}
