package com.vetc.manage.service.impl;

import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.dto.request.AccountLimitDto;
import com.vetc.manage.entity.AccountLimit;
import com.vetc.manage.entity.Product;
import com.vetc.manage.entity.Wallet;
import com.vetc.manage.entity.view.VAccountLimit;
import com.vetc.manage.enums.ExceptionEnum;
import com.vetc.manage.exception.CustomizeException;
import com.vetc.manage.repository.AccountLimitRepository;
import com.vetc.manage.repository.ProductRepository;
import com.vetc.manage.repository.WalletRepository;
import com.vetc.manage.repository.view.VAccountLimitRepository;
import com.vetc.manage.service.AccountLimitService;
import com.vetc.manage.service.common.ServiceBase;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountLimitServiceImpl
    extends ServiceBase<AccountLimit, Long, AccountLimitRepository>
    implements AccountLimitService {

  @Autowired
  private AccountLimitRepository accountLimitRepository;

  @Autowired
  private WalletRepository walletRepository;
  @Autowired
  private VAccountLimitRepository vAccountLimitRepository;

  @Autowired
  private ProductRepository productRepository;

  @Value("${server.account.limit.hm-day}")
  private String hmDay;

  @Value("${server.account.limit.hm-month}")
  private String hmMonth;

  @Value("${server.account.limit.hm-trans}")
  private String hmTrans;

  @Value("${server.account.admin}")
  private String accountAdmin;

  @Override
  public ListResult<AccountLimit> search(SearchBase searchBase) throws Exception {
    return super.search(searchBase);
  }

  @Override
  public List<VAccountLimit> searchVAccountLimit(Long id) throws Exception {
    List<VAccountLimit> lsResult = vAccountLimitRepository.getByCustId(id);
    if (!lsResult.isEmpty()) {

      // list id product
      List<Long> productIds = lsResult.stream().filter(item -> item.getProductId() != null)
          .map(item -> item.getProductId()).collect(Collectors.toList()).stream().distinct()
          .collect(Collectors.toList());

      //list product
      List<Product> lsProduct = productRepository.findByIds(productIds);

      //Convert to Map
      //Key = Id
      // Value = object
      Map<Long, Product> getProducts = lsProduct.stream()
          .collect(Collectors.toMap(Product::getId, Function.identity()));

      lsResult.stream().filter(item -> item.getProductId() != null).forEach(item -> {
        item.setNameProductTypeRef(getProducts.get(item.getProductId()).getNotes());
      });
    }
    return lsResult;
  }

  @Override
  public AccountLimit get(Long id) {
    return super.get(id);
  }


  @Override
  public AccountLimit save(AccountLimit object) throws Exception {
    return super.save(object);
  }

  @Override
  @Transactional
  public AccountLimitDto updateManage(AccountLimitDto object) throws Exception {
    Wallet wallet = walletRepository.findByCustId(object.getCustId());
    if (Objects.isNull(wallet)) {
      throw new CustomizeException(
          ExceptionEnum.DATA_INVALID.getCode(), "Không tìm thấy ví!");
    }

    if (object.getHmNgay() != null) {
      AccountLimit accountLimit = getAccountLimit(hmDay, object, object.getHmNgay(), wallet);
      setupAccountLimit(accountLimit, object, object.getHmNgay());
      super.save(accountLimit);
    }
    if (object.getHmThang() != null) {
      AccountLimit accountLimit = getAccountLimit(hmMonth, object, object.getHmThang(), wallet);
      setupAccountLimit(accountLimit, object, object.getHmThang());
      super.save(accountLimit);
    }
    if (object.getHmGg() != null) {
      AccountLimit accountLimit = getAccountLimit(hmTrans, object, object.getHmGg(), wallet);
      setupAccountLimit(accountLimit, object, object.getHmGg());
      super.save(accountLimit);
    }
    return object;
  }

  private void setupAccountLimit(AccountLimit accountLimit, AccountLimitDto object,
      String object1) {
    accountLimit.setChannel(object.getChannel());
    accountLimit.setProductId(object.getProductId());
    accountLimit.setLimitMaxValue(object1);
  }

  @Override
  @Transactional
  public AccountLimitDto deleteManage(AccountLimitDto object) throws Exception {
    boolean checkStatus = false;
    if (object.getHmNgay() != null) {
      AccountLimit accountLimit = checkAccountLimitBeforeDelete(object, hmDay);
      if (accountLimit != null) {
        super.delete(accountLimit.getId());
        checkStatus = true;
      }
    }
    if (object.getHmThang() != null) {
      AccountLimit accountLimit = checkAccountLimitBeforeDelete(object, hmMonth);
      if (accountLimit != null) {
        super.delete(accountLimit.getId());
        checkStatus = true;
      }
    }
    if (object.getHmGg() != null) {
      AccountLimit accountLimit = checkAccountLimitBeforeDelete(object, hmTrans);
      if (accountLimit != null) {
        super.delete(accountLimit.getId());
        checkStatus = true;
      }
    }
    if(checkStatus)
    return object;
    else
      throw new CustomizeException(ExceptionEnum.RECORD_NOT_FOUND.getCode(), "Không tìm thấy bản ghi !");
  }

  @Override
  @Transactional
  public AccountLimitDto saveManage(AccountLimitDto object) throws Exception {
    Wallet wallet = walletRepository.findByCustId(object.getCustId());
    if (Objects.isNull(wallet)) {
      throw new CustomizeException(
          ExceptionEnum.DATA_INVALID.getCode(), "Không tìm thấy ví!");
    }

    if (object.getHmNgay() != null) {
      if (checkAccountLimit(object.getProductId(), object.getChannel(), object.getCustId(),
          hmDay)) {
        super.save(
            setAccountLimitBeforeSave(wallet, hmDay, object.getHmNgay(), object));
      } else {
        throw new CustomizeException(ExceptionEnum.RECORD_NOT_FOUND.getCode(),
            "Cấu hình hạn mức này đã tồn tại!");
      }
    }
    if (object.getHmThang() != null) {
      if (checkAccountLimit(object.getProductId(), object.getChannel(), object.getCustId(),
          hmMonth)) {
        super.save(
            setAccountLimitBeforeSave(wallet, hmMonth, object.getHmNgay(), object));
      } else {
        throw new CustomizeException(ExceptionEnum.RECORD_NOT_FOUND.getCode(),
            "Cấu hình hạn mức này đã tồn tại!");
      }
    }
    if (object.getHmGg() != null) {
      if (checkAccountLimit(object.getProductId(), object.getChannel(), object.getCustId(),
          hmTrans)) {
        super.save(
            setAccountLimitBeforeSave(wallet, hmTrans, object.getHmNgay(), object));
      } else {
        throw new CustomizeException(ExceptionEnum.RECORD_NOT_FOUND.getCode(),
            "Cấu hình hạn mức này đã tồn tại!");
      }
    }
    return object;
  }

  private AccountLimit setAccountLimitBeforeSave(Wallet wallet, String type, String value,
      AccountLimitDto accountLimitDto)
      throws Exception {
    return AccountLimit.builder().walletId(wallet.getId()).custId(accountLimitDto.getCustId())
        .walletType(String.valueOf(wallet.getWalletTypeId()))
        .limitType(type).channel(accountLimitDto.getChannel())
        .limitMaxValue(value).limitMinValue("0").createdBy(accountAdmin)
        .createdAt(new Date()).status("1").productId(accountLimitDto.getProductId()).build();
  }

  @Override
  public AccountLimit update(AccountLimit object, Long id) throws Exception {
    return super.update(object, id);
  }

  @Override
  public AccountLimit delete(Long id) throws Exception {
    return super.delete(id);
  }

  @Override
  public List<AccountLimit> getAll() {
    List<AccountLimit> lsResult = super.getAll();
    return lsResult;
  }

  private AccountLimit getAccountLimit(String type, AccountLimitDto accountLimitDto, String value,
      Wallet wallet) {
    AccountLimit accountLimit = accountLimitRepository
        .getAccountLimitByMultiField(accountLimitDto.getProductId(),
            accountLimitDto.getChannel(), accountLimitDto.getCustId(), type);
    if (Objects.isNull(accountLimit)) {
      return AccountLimit.builder().walletId(wallet.getId())
          .walletType(String.valueOf(wallet.getWalletTypeId())).limitType(type)
          .limitMaxValue(value).limitMinValue("0").createdBy(accountAdmin)
          .custId(accountLimitDto.getCustId()).channel(accountLimitDto.getChannel())
          .createdAt(new Date()).status("1").productId(accountLimitDto.getProductId()).build();
    }
    return accountLimit;
  }

  private boolean checkAccountLimit(Long productId, String channel, Long cusId, String type) {
    AccountLimit accountLimit = accountLimitRepository
        .getAccountLimitByMultiField(productId, channel, cusId, type);
    if (Objects.isNull(accountLimit)) {
      return true;
    } else {

      return false;
    }
  }

  private AccountLimit checkAccountLimitBeforeDelete(AccountLimitDto accountLimitDto,
      String type) {
    AccountLimit accountLimit = accountLimitRepository
        .getAccountLimitByMultiField(accountLimitDto.getProductId(),
            accountLimitDto.getChannel(), accountLimitDto.getCustId(), type);
    return accountLimit;

  }
}
