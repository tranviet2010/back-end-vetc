package com.vetc.manage.service;

import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.entity.AccountLink;
import com.vetc.manage.entity.view.VAccountLink;
import com.vetc.manage.entity.view.VAccountLinkDetail;
import java.util.List;
import javax.xml.bind.ValidationException;

public interface AccountLinkService {

  ListResult<AccountLink> search(SearchBase searchBase) throws Exception;


  List<VAccountLinkDetail> getVAccountLinkDetail(Long id, Long autoId);

  ListResult<VAccountLink> searchVAccountLink(SearchBase searchBase)
      throws ValidationException;

  AccountLink save(AccountLink object) throws Exception;

  AccountLink update(AccountLink object, Long id) throws Exception;

  AccountLink delete(Long id) throws Exception;

  List<AccountLink> getAll();
}
