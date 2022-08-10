package com.vetc.manage.service;

import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.dto.transactiondto.TransactionDepositAndWithDrawDetailDto;
import com.vetc.manage.dto.transactiondto.TransactionDepositAndWithDrawDto;
import com.vetc.manage.dto.transactiondto.TransactionInternalAndElecFundsTransferDetailDto;
import com.vetc.manage.dto.transactiondto.TransactionInternalElecFundsTransferDto;
import com.vetc.manage.dto.transactiondto.TransactionPayDetailDto;
import com.vetc.manage.dto.transactiondto.TransactionPayDto;
import com.vetc.manage.entity.view.VTransaction;
import java.util.List;
import java.util.Map;

public interface TransactionService {


  TransactionInternalAndElecFundsTransferDetailDto getTransactionInternalDetail(Long transId,
      String productCode);

  TransactionDepositAndWithDrawDetailDto getTransactionDepositorWithDrawDetail(Long transId,
      String productCode);

  TransactionPayDetailDto getTransactionPayDetailDto(Long transId, String productCode);

  List<VTransaction> searchAll(Map<String, String> requestFilter);

  ListResult<VTransaction> search(SearchBase searchBase) throws Exception;

  ListResult<TransactionDepositAndWithDrawDto> searchTransactionDepositOrWithDraw(
      SearchBase searchBase) throws Exception;

  ListResult<TransactionInternalElecFundsTransferDto> searchTransactionInternal(
      SearchBase searchBase) throws Exception;


  ListResult<TransactionPayDto> searchTransactionPay(SearchBase searchBase) throws Exception;

  VTransaction get(Long id);

  VTransaction save(VTransaction object) throws Exception;

  VTransaction update(VTransaction object, Long id) throws Exception;
}
