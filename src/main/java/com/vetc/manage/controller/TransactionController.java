package com.vetc.manage.controller;

import com.vetc.manage.controller.base.ControllerBase;
import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.dto.ResponseDto;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.dto.transactiondto.TransactionDepositAndWithDrawDetailDto;
import com.vetc.manage.dto.transactiondto.TransactionDepositAndWithDrawDto;
import com.vetc.manage.dto.transactiondto.TransactionInternalAndElecFundsTransferDetailDto;
import com.vetc.manage.dto.transactiondto.TransactionInternalElecFundsTransferDto;
import com.vetc.manage.dto.transactiondto.TransactionPayDetailDto;
import com.vetc.manage.dto.transactiondto.TransactionPayDto;
import com.vetc.manage.service.TransactionService;
import com.vetc.manage.swagger.VTransactionDepositResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Transaction / giao dịch ")
@RequestMapping("/api")
@RestController
@Slf4j
public class TransactionController extends ControllerBase {

  @Autowired
  private TransactionService transactionDepositService;

  @ApiOperation(value = "search deposit", tags = "search nạp tiền")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = VTransactionDepositResult.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/trans-deposit", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> searchDeposit(
      @RequestParam(value = "page", required = false) Integer pageNo,
      @RequestParam(value = "limit", required = false) Integer limit,
      @RequestParam(value = "order_field", required = false) String orderField,
      @RequestParam(value = "order_type", defaultValue = "false", required = false) boolean orderType,
      @RequestParam(value = "productCode", defaultValue = "deposit") String deposit)
      throws Exception {
    // tao doi duong tim kiem co ban.
    SearchBase searchBase = buildSearchBase(pageNo, limit, orderField, orderType);
    // them cac thuoc tinh tim kiem
    addSearchField(searchBase, request.getParameterNames(), null);
    // goi ham tim kiem co ban
    ListResult<TransactionDepositAndWithDrawDto> lsResult = transactionDepositService.searchTransactionDepositOrWithDraw(
        searchBase);
    // tra ke qua
    return responseOK(lsResult);

  }

  @ApiOperation(value = "search with draw", tags = "search rút tiền")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = VTransactionDepositResult.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/trans-withdraw", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> searchWithDraw(
      @RequestParam(value = "page", required = false) Integer pageNo,
      @RequestParam(value = "limit", required = false) Integer limit,
      @RequestParam(value = "order_field", required = false) String orderField,
      @RequestParam(value = "order_type", defaultValue = "false", required = false) boolean orderType,
      @RequestParam(value = "productCode", defaultValue = "withDraw") String withDraw)
      throws Exception {
    // tao doi duong tim kiem co ban.
    SearchBase searchBase = buildSearchBase(pageNo, limit, orderField, orderType);
    // them cac thuoc tinh tim kiem
    addSearchField(searchBase, request.getParameterNames(), null);
    // goi ham tim kiem co ban
    ListResult<TransactionDepositAndWithDrawDto> lsResult = transactionDepositService.searchTransactionDepositOrWithDraw(
        searchBase);
    // tra ke qua
    return responseOK(lsResult);
  }

//  @ApiOperation(value = "search internal transfer /  chuyển tiền ví ví", tags = "search")
//  @ApiResponses(value = {
//      @ApiResponse(code = 200, message = "Success", response = TransactionInternalElecFundsTransferDto.class),
//      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
//      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
//  @RequestMapping(value = "/internal-transfer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
//      MediaType.ALL_VALUE})
//  public ResponseEntity<Object> searchInternalTransfer(
//      @RequestParam(value = "page", required = false) Integer pageNo,
//      @RequestParam(value = "limit", required = false) Integer limit,
//      @RequestParam(value = "order_field", required = false) String orderField,
//      @RequestParam(value = "order_type", defaultValue = "false", required = false) boolean orderType,
//      @RequestParam(value = "productCode", defaultValue = "internalTransfer") String internalTransfer)
//      throws Exception {
//    // tao doi duong tim kiem co ban.
//    SearchBase searchBase = buildSearchBase(pageNo, limit, orderField, orderType);
//    // them cac thuoc tinh tim kiem
//    addSearchField(searchBase, request.getParameterNames(), null);
//    // goi ham tim kiem co ban
//    ListResult<TransactionInternalElecFundsTransferDto> lsResult = transactionDepositService.searchTransactionInternal(
//        searchBase);
//    // tra ke qua
//    return responseOK(lsResult);
//  }

  @ApiOperation(value = "search elecFundsTransfer - chuyển tiền điện tử", tags = "search chuyển tiền điện tử")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = TransactionInternalElecFundsTransferDto.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/elec-funds-transfer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> searchElectFundsTransfer(
      @RequestParam(value = "page", required = false) Integer pageNo,
      @RequestParam(value = "limit", required = false) Integer limit,
      @RequestParam(value = "order_field", required = false) String orderField,
      @RequestParam(value = "order_type", defaultValue = "false", required = false) boolean orderType,
      @RequestParam(value = "productCode", defaultValue = "elecFundsTransfer") String withDraw)
      throws Exception {
    // tao doi duong tim kiem co ban.
    SearchBase searchBase = buildSearchBase(pageNo, limit, orderField, orderType);
    // them cac thuoc tinh tim kiem
    addSearchField(searchBase, request.getParameterNames(), null);
    // goi ham tim kiem co ban
    ListResult<TransactionInternalElecFundsTransferDto> lsResult = transactionDepositService.searchTransactionInternal(
        searchBase);
    // tra ke qua
    return responseOK(lsResult);
  }

  @ApiOperation(value = "search pay - thanh toán", tags = "search thanh toán")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = TransactionPayDto.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/pay-transfer", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> searchPayTransfer(
      @RequestParam(value = "page", required = false) Integer pageNo,
      @RequestParam(value = "limit", required = false) Integer limit,
      @RequestParam(value = "order_field", required = false) String orderField,
      @RequestParam(value = "order_type", defaultValue = "false", required = false) boolean orderType)
      throws Exception {
    // tao doi duong tim kiem co ban.
    SearchBase searchBase = buildSearchBase(pageNo, limit, orderField, orderType);
    // them cac thuoc tinh tim kiem
//    request.setAttribute("productCode", "bookTicket");
    addSearchField(searchBase, request.getParameterNames(), null);

    // goi ham tim kiem co ban
    ListResult<TransactionPayDto> lsResult =
        transactionDepositService.searchTransactionPay(searchBase);
    // tra ke qua
    return responseOK(lsResult);
  }


  @ApiOperation(value = "get detail deposit", tags = "get detail nạp tiền")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = TransactionDepositAndWithDrawDetailDto.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/trans-deposit/detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> getDepositDetail(
      @RequestParam(value = "trans_id", required = true) Long transId,
      @RequestParam(value = "product_code", required = false, defaultValue = "deposit") String productCode)
      throws Exception {
    String clientIp = clientInfo.getClientIp();
    log.info(">>> [get] START <<< [clientIp] = {}", clientIp);
    TransactionDepositAndWithDrawDetailDto result = transactionDepositService.getTransactionDepositorWithDrawDetail(
        transId, productCode);
    return responseOK(result);
  }

//  @ApiOperation(value = "get internal detail / chuyển tiền ví ví ", tags = "get internal detail")
//  @ApiResponses(value = {
//      @ApiResponse(code = 200, message = "Success", response = TransactionInternalAndElecFundsTransferDetailDto.class),
//      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
//      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
//  @RequestMapping(value = "/internal-transfer/detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
//      MediaType.ALL_VALUE})
//  public ResponseEntity<Object> getInternalTransferDetail(
//      @RequestParam(value = "trans_id", required = true) Long transId,
//      @RequestParam(value = "product_code", required = false, defaultValue = "internalTransfer") String productCode)
//      throws Exception {
//    String clientIp = clientInfo.getClientIp();
//    log.info(">>> [get] START <<< [clientIp] = {}", clientIp);
//    TransactionInternalAndElecFundsTransferDetailDto result = transactionDepositService.getTransactionInternalDetail(
//        transId, productCode);
//    return responseOK(result);
//  }

  @ApiOperation(value = "get with draw detail / rút tiền", tags = "get detail")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = TransactionDepositAndWithDrawDetailDto.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/trans-withdraw/detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> getWithDrawDetail(
      @RequestParam(value = "trans_id", required = true) Long transId,
      @RequestParam(value = "product_code", required = false, defaultValue = "withDraw") String productCode)
      throws Exception {
    String clientIp = clientInfo.getClientIp();
    log.info(">>> [get] START <<< [clientIp] = {}", clientIp);
    TransactionDepositAndWithDrawDetailDto result = transactionDepositService.getTransactionDepositorWithDrawDetail(
        transId, productCode);
    return responseOK(result);
  }

  @ApiOperation(value = "get with elecFundsTransfer / chuyển tiền điện tử", tags = "get detail")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = TransactionInternalElecFundsTransferDto.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/elec-funds-transfer/detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> getElecFundsTransfer(
      @RequestParam(value = "trans_id", required = true) Long transId,
      @RequestParam(value = "product_code", required = false, defaultValue = "elecFundsTransfer") String productCode)
      throws Exception {
    String clientIp = clientInfo.getClientIp();
    log.info(">>> [get] START <<< [clientIp] = {}", clientIp);
    TransactionDepositAndWithDrawDetailDto result = transactionDepositService.getTransactionDepositorWithDrawDetail(
        transId, productCode);
    return responseOK(result);
  }

  @ApiOperation(value = "pay/ thanh toán / chuyển tiền điện tử", tags = "get detail")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = TransactionPayDetailDto.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/pay-transfer/detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> getPayTransfer(
      @RequestParam(value = "trans_id", required = true) Long transId,
      @RequestParam(value = "product_code", required = false) String productCode)
      throws Exception {
    String clientIp = clientInfo.getClientIp();
    log.info(">>> [get] START <<< [clientIp] = {}", clientIp);
    TransactionPayDetailDto result = transactionDepositService.getTransactionPayDetailDto(
        transId, productCode);
    return responseOK(result);
  }


}
