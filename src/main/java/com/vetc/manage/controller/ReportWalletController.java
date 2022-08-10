package com.vetc.manage.controller;

import com.vetc.manage.controller.base.ControllerBase;
import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.dto.ResponseDto;
import com.vetc.manage.entity.view.VReportTwoTransfer;
import com.vetc.manage.entity.view.VReportWallet;
import com.vetc.manage.entity.view.VReportWalletPhase;
import com.vetc.manage.service.ReportWalletService;
import com.vetc.manage.swagger.VTransactionDepositResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Report Wallet / Báo cáo ví")
@RequestMapping("/api")
@RestController
@Slf4j
public class ReportWalletController extends ControllerBase {

  @Autowired
  private ReportWalletService reportWalletService;

  @ApiOperation(value = "search report amount wallet // Số lượng ví", tags = "search nạp tiền")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = VTransactionDepositResult.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Ba- Request", response = ResponseDto.class)})
  @RequestMapping(value = "/report_wallet/amount", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> searchSum(
      @RequestParam(value = "page", defaultValue = "1", required = false) Integer pageNo,
      @RequestParam(value = "limit", defaultValue = "10", required = false) Integer limit,
      @RequestParam(required = false) Map<String, String> request)
      throws Exception {

    ListResult<VReportWallet> lsResult = reportWalletService.searchSum(request, limit, pageNo);
    // tra ke qua
    return responseOK(lsResult);
  }

  @ApiOperation(value = "search report phase wallet // theo kỳl", tags = "search report phase wallet")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = VTransactionDepositResult.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/report-wallet/phase", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> searchPhase(
      @RequestParam(value = "page", defaultValue = "1", required = false) Integer pageNo,
      @RequestParam(value = "limit", defaultValue = "10", required = false) Integer limit,
      @RequestParam(required = false)Map<String, String> request)
      throws Exception {
    ListResult<VReportWalletPhase> lsResult = reportWalletService.searchPhase(request, limit, pageNo);
    // tra ke qua
    return responseOK(lsResult);
  }

  @ApiOperation(value = "search report debt difference //  Giao dịch 2 bên", tags = "search report phase wallet")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = VTransactionDepositResult.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/report-wallet/debt-difference", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> searchDebtDifference(
      @RequestParam(value = "page", defaultValue = "1", required = false) Integer pageNo,
      @RequestParam(value = "limit", defaultValue = "10", required = false) Integer limit,
      @RequestParam(required = false)Map<String, String> request)
      throws Exception {
    ListResult<VReportTwoTransfer> lsResult = reportWalletService.searchReportWalletTwoTransfer(request, limit, pageNo);
    // tra ke qua
    return responseOK(lsResult);
  }

}
