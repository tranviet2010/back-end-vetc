package com.vetc.manage.service.impl;

import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.entity.AccountLink;
import com.vetc.manage.entity.WalletType;
import com.vetc.manage.entity.view.VAccountLink;
import com.vetc.manage.entity.view.VAccountLinkDetail;
import com.vetc.manage.enums.ExceptionEnum;
import com.vetc.manage.exception.CustomizeException;
import com.vetc.manage.exception.ErrorListException;
import com.vetc.manage.repository.AccountLinkRepository;
import com.vetc.manage.repository.WalletTypeRepository;
import com.vetc.manage.repository.view.VAccountLinkDetailRepository;
import com.vetc.manage.repository.view.VAccountLinkRepository;
import com.vetc.manage.service.AccountLinkService;
import com.vetc.manage.service.common.ServiceBase;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class AccountLinkServiceImpl
    extends ServiceBase<AccountLink, Long, AccountLinkRepository>
    implements AccountLinkService {

  @Autowired
  private VAccountLinkRepository vAccountLinkRepository;

  @Autowired
  private VAccountLinkDetailRepository vAccountLinkDetailRepository;

  @Autowired
  private WalletTypeRepository walletTypeRepository;

  @Override
  public ListResult<AccountLink> search(SearchBase searchBase) throws Exception {
    return super.search(searchBase);
  }


  @Override
  public List<VAccountLinkDetail> getVAccountLinkDetail(Long custId, Long codeTrans) {
    List<VAccountLinkDetail> vAccountLinkDetails = vAccountLinkDetailRepository.getListAccountLinkDetail(
        custId, codeTrans);
    if (vAccountLinkDetails.isEmpty()) {
      throw new CustomizeException(ExceptionEnum.RECORD_NOT_FOUND.getCode(),
          "Không có liên kết nào!", HttpStatus.NOT_FOUND);
    }

    List<WalletType> walletTypes = walletTypeRepository.getWalletTypeNote();

    Map<Long, WalletType> mapWalletTypeDto = walletTypes.stream()
        .filter(item -> item.getNotes() != null)
        .collect(Collectors.toMap(WalletType::getId, Function.identity()));

    vAccountLinkDetails.stream().filter(item -> item.getCodeTrans() != null).forEach(item -> {
      item.setWalletTypeName(mapWalletTypeDto.get(item.getWalletType()).getNotes());
    });

    return vAccountLinkDetails;
  }

  @Override
  public ListResult<VAccountLink> searchVAccountLink(SearchBase searchBase)
      throws ValidationException {
    ListResult<VAccountLink> result = vAccountLinkRepository.search(searchBase);
    return result;
  }


  @Override
  public AccountLink save(AccountLink object) throws Exception {
    return super.save(object);
  }

  @Override
  public AccountLink update(AccountLink object, Long id) throws Exception {
    return super.update(object, id);
  }

  @Override
  public AccountLink delete(Long id) throws Exception {
    return super.delete(id);
  }

  @Override
  public List<AccountLink> getAll() {
    return super.getAll();
  }
}
