package com.vetc.manage.service;

import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.dto.request.AccountLimitDto;
import com.vetc.manage.entity.AccountLimit;
import com.vetc.manage.entity.view.VAccountLimit;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface AccountLimitService {

  ListResult<AccountLimit> search(SearchBase searchBase) throws Exception;

  List<VAccountLimit> searchVAccountLimit(Long id) throws Exception;

  AccountLimit get(Long id);

  AccountLimit save(AccountLimit object) throws Exception;

  @Transactional
  AccountLimitDto updateManage(AccountLimitDto object) throws Exception;

  @Transactional
  AccountLimitDto deleteManage(AccountLimitDto object) throws Exception;

  @Transactional
  AccountLimitDto saveManage(AccountLimitDto object) throws Exception;

  AccountLimit update(AccountLimit object, Long id) throws Exception;

  AccountLimit delete(Long id) throws Exception;

  List<AccountLimit> getAll();
}
