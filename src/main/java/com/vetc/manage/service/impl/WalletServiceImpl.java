package com.vetc.manage.service.impl;

import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.dto.request.WalletDto;
import com.vetc.manage.entity.Customer;
import com.vetc.manage.entity.CustomerInfo;
import com.vetc.manage.entity.Wallet;
import com.vetc.manage.entity.WalletType;
import com.vetc.manage.enums.ExceptionEnum;
import com.vetc.manage.exception.ValidationException;
import com.vetc.manage.repository.CustVehicleRepository;
import com.vetc.manage.repository.CustomerInfoRepository;
import com.vetc.manage.repository.CustomerRepository;
import com.vetc.manage.repository.WalletRepository;
import com.vetc.manage.repository.WalletTypeRepository;
import com.vetc.manage.service.WalletService;
import com.vetc.manage.service.common.ServiceBase;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletServiceImpl
    extends ServiceBase<Wallet, Long, WalletRepository>
    implements WalletService {

  @Autowired
  private CustVehicleRepository custVehicleRepository;

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private CustomerInfoRepository customerInfoRepository;

  @Autowired
  private WalletTypeRepository walletTypeRepository;

  @Override
  public ListResult<Wallet> search(SearchBase searchBase) throws Exception {
    return super.search(searchBase);
  }

  @Override
  public Wallet get(Long id) {
    return super.get(id);
  }


  @Override
  @Transactional
  public Wallet saveWalletAndCustomerAndCustomerInfo(WalletDto object) throws Exception {
    if (Objects.isNull(object)) {
      throw new ValidationException(ExceptionEnum.DATA_INVALID.getCode(),
          ExceptionEnum.DATA_INVALID.getMessage());
    }

    Customer customer = Customer.builder().name(object.getName()).mobiNumber(object.getMobiNumber())
        .gender(object.getGender()).idNo(object.getIdNo()).idIssueDate(object.getIdIssueDate())
        .idIssuePlace(object.getIdIssuePlace()).address(object.getAddress())
        .homeTown(object.getHomeTow()).nationality(object.getNationality())
        .avatar(object.getAvatar()).verifyState("NOT_VERIFIED").build();

    Customer newCustomer = customerRepository.save(customer);
    WalletType walletType = walletTypeRepository.findById(object.getWalletTypeId()).orElse(null);
    if (walletType == null) {
      throw new ValidationException(ExceptionEnum.RECORD_NOT_FOUND.getCode(),
          ExceptionEnum.RECORD_NOT_FOUND.getMessage());
    }

    Long moneyDefault = new Long(0);
    Wallet wallet = Wallet.builder().balance(moneyDefault).blockBalance(moneyDefault)
        .availableBalance(moneyDefault).minimumBalance(moneyDefault).lastModifiedDate(new Date())
        .walletTypeCode(walletType.getCode()).state("OPEN").status("1")
        .walletTypeId(object.getWalletTypeId()).custId(newCustomer.getId()).build();
    super.save(wallet);

    CustomerInfo customerInfoFont = CustomerInfo.builder().cusId(newCustomer.getId())
        .docId(new Long(1))
        .docType("CMND").createdAt(new Date()).modifiedAt(new Date()).state(new Long(1))
        .url(object.getIdNoFont()).build();

    CustomerInfo customerInfoBack = customerInfoFont;
    customerInfoBack.setUrl(object.getIdNoBack());

    List<CustomerInfo> customerInfoList = new ArrayList<>();
    customerInfoList.add(customerInfoFont);
    customerInfoList.add(customerInfoBack);

    customerInfoRepository.saveAll(customerInfoList);

    return wallet;
  }

  @Override
  public Wallet save(Wallet object) throws Exception {
    return super.save(object);
  }

//  @Override
//  public WalletDto saveNewWallet(WalletDto walletDto) {
//
//  }

  @Override
  public Wallet update(Wallet object, Long id) throws Exception {
    return super.update(object, id);
  }

  @Override
  public Wallet delete(Long id) throws Exception {
    return super.delete(id);
  }

  @Override
  public List<Wallet> getAll() {
    return super.getAll();
  }
}
