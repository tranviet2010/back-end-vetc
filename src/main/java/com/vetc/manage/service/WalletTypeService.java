package com.vetc.manage.service;

import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.entity.WalletType;
import java.util.List;

public interface WalletTypeService {

  ListResult<WalletType> search(SearchBase searchBase) throws Exception;

  WalletType get(Long id);

  WalletType save(WalletType object) throws Exception;

  WalletType update(WalletType object, Long id) throws Exception;

  WalletType delete(Long id) throws Exception;

  List<WalletType> getAll();
}
