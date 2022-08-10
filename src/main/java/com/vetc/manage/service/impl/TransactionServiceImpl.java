package com.vetc.manage.service.impl;

import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.dto.transactiondto.TransactionDepositAndWithDrawDetailDto;
import com.vetc.manage.dto.transactiondto.TransactionDepositAndWithDrawDto;
import com.vetc.manage.dto.transactiondto.TransactionInternalAndElecFundsTransferDetailDto;
import com.vetc.manage.dto.transactiondto.TransactionInternalElecFundsTransferDto;
import com.vetc.manage.dto.transactiondto.TransactionPayDetailDto;
import com.vetc.manage.dto.transactiondto.TransactionPayDto;
import com.vetc.manage.entity.view.VTransaction;
import com.vetc.manage.entity.view.VTransactionDetail;
import com.vetc.manage.enums.ExceptionEnum;
import com.vetc.manage.exception.CustomizeException;
import com.vetc.manage.repository.view.VTransactionDetailRepository;
import com.vetc.manage.repository.view.VTransactionRepository;
import com.vetc.manage.service.TransactionService;
import com.vetc.manage.service.common.ServiceBase;
import com.vetc.manage.utils.BEUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl
    extends ServiceBase<VTransaction, Long, VTransactionRepository>
    implements TransactionService {

  @Autowired
  private VTransactionDetailRepository vTransactionDepositDetailRepository;

  @Autowired
  private VTransactionRepository vTransactionRepository;


  @Override
  public TransactionInternalAndElecFundsTransferDetailDto getTransactionInternalDetail(Long transId,
      String productCode) {
    VTransactionDetail vTransactionDetail =
        vTransactionDepositDetailRepository.getVTransactionDetailByTransIdAndProductCode(
            transId, productCode);
    if (Objects.isNull(vTransactionDetail)) {
      throw new CustomizeException(ExceptionEnum.RECORD_NOT_FOUND.getCode(),
          "Không tìm thấy bản ghi này!");
    }
    TransactionInternalAndElecFundsTransferDetailDto transactionInternalDetailDto = new TransactionInternalAndElecFundsTransferDetailDto();
    BEUtils.copyMatchingFields(vTransactionDetail, transactionInternalDetailDto);
    return transactionInternalDetailDto;
  }

  @Override
  public TransactionDepositAndWithDrawDetailDto getTransactionDepositorWithDrawDetail(Long transId,
      String productCode) {
    VTransactionDetail vTransactionDepositDetail =
        vTransactionDepositDetailRepository.getVTransactionDetailByTransIdAndProductCode(
            transId, productCode);
    if (Objects.isNull(vTransactionDepositDetail)) {
      throw new CustomizeException(ExceptionEnum.RECORD_NOT_FOUND.getCode(),
          "Không tìm thấy bản ghi này!");
    }
    TransactionDepositAndWithDrawDetailDto transactionDepositDto = new TransactionDepositAndWithDrawDetailDto();
    BEUtils.copyMatchingFields(vTransactionDepositDetail, transactionDepositDto);
    return transactionDepositDto;
  }

  @Override
  public TransactionPayDetailDto getTransactionPayDetailDto(Long transId, String productCode) {
    VTransactionDetail vTransactionDepositDetail =
        vTransactionDepositDetailRepository.getVTransactionDetailByTransIdAndProductCode(
            transId, productCode);
    if (Objects.isNull(vTransactionDepositDetail)) {
      throw new CustomizeException(ExceptionEnum.RECORD_NOT_FOUND.getCode(),
          "Không tìm thấy bản ghi này!");
    }
    TransactionPayDetailDto transactionDepositDto = new TransactionPayDetailDto();
    BEUtils.copyMatchingFields(vTransactionDepositDetail, transactionDepositDto);
    return transactionDepositDto;
  }

  @Override
  public List<VTransaction> searchAll(Map<String, String> requestFilter) {
    return super.searchAll(requestFilter);
  }

  @Override
  public ListResult<VTransaction> search(SearchBase searchBase) throws Exception {
    return super.search(searchBase);
  }

  @Override
  public ListResult<TransactionDepositAndWithDrawDto> searchTransactionDepositOrWithDraw(
      SearchBase searchBase) throws Exception {
    ListResult<VTransaction> lsResult = super.search(searchBase);
    ListResult result = lsResult;
    List<TransactionDepositAndWithDrawDto> transactionDepositDtos = new ArrayList<>();
    lsResult.getData().forEach(item -> {
      TransactionDepositAndWithDrawDto transactionDepositDto = new TransactionDepositAndWithDrawDto();
      BEUtils.copyMatchingFields(item, transactionDepositDto);
      transactionDepositDtos.add(transactionDepositDto);
    });
    result.setData(transactionDepositDtos);
    return result;
  }

  @Override
  public ListResult<TransactionInternalElecFundsTransferDto> searchTransactionInternal(
      SearchBase searchBase)
      throws Exception {
    ListResult<VTransaction> lsResult = super.search(searchBase);
    ListResult result = lsResult;
    List<TransactionInternalElecFundsTransferDto> transactionInternalDtos = new ArrayList<>();
    lsResult.getData().forEach(item -> {
      TransactionInternalElecFundsTransferDto transactionDepositDto = new TransactionInternalElecFundsTransferDto();
      BEUtils.copyMatchingFields(item, transactionDepositDto);
      transactionInternalDtos.add(transactionDepositDto);
    });

    result.setData(transactionInternalDtos);
    return result;
  }

  @Override
  public ListResult<TransactionPayDto> searchTransactionPay(SearchBase searchBase)
      throws Exception {
    ListResult<VTransaction> se = super.search(searchBase);
    ListResult result = se;
    List<TransactionPayDto> transactionPayDtos = new ArrayList<>();
    se.getData().forEach(item -> {
      TransactionPayDto transactionPayDto = new TransactionPayDto();
      BEUtils.copyMatchingFields(item, transactionPayDto);
      transactionPayDtos.add(transactionPayDto);
    });
    result.setData(transactionPayDtos);
    return result;
  }

  @Override
  public VTransaction get(Long id) {
    return super.get(id);
  }

  @Override
  public VTransaction save(VTransaction object) throws Exception {
    return super.save(object);
  }

  @Override
  public VTransaction update(VTransaction object, Long id) throws Exception {
    return super.update(object, id);
  }

  @Override
  public VTransaction delete(Long id) throws Exception {
    return super.delete(id);
  }

  @Override
  public List<VTransaction> searchByList(String field, List<String> items) {
    return super.searchByList(field, items);
  }

  @Override
  public List<VTransaction> saveAll(List<VTransaction> list) {
    return super.saveAll(list);
  }

  @Override
  public List<VTransaction> getAll() {
    return super.getAll();
  }
}
