package com.vetc.manage.service;

import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.dto.request.WalletDto;
import com.vetc.manage.entity.Wallet;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

public interface WalletService {

  ListResult<Wallet> search(SearchBase searchBase) throws Exception;

  Wallet get(Long id);

  Wallet save(Wallet object) throws Exception;

  @Transactional
  Wallet saveWalletAndCustomerAndCustomerInfo(WalletDto object) throws Exception;

  Wallet update(Wallet object, Long id) throws Exception;

  Wallet delete(Long id) throws Exception;

  List<Wallet> getAll();
}
