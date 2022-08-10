package com.vetc.manage.controller;

import com.vetc.manage.controller.base.ControllerBase;
import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.dto.ResponseDto;
import com.vetc.manage.dto.request.AccountLimitDto;
import com.vetc.manage.entity.AccountLimit;
import com.vetc.manage.entity.view.VAccountLimit;
import com.vetc.manage.service.AccountLimitService;
import com.vetc.manage.swagger.VAccountLimitResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Hạn mức / AccountLimit")
@RequestMapping("/api")
@RestController
@Slf4j
public class AccountLimitController extends ControllerBase {

  @Autowired
  private AccountLimitService accountLimitService;


  @ApiOperation(value = "get detail", tags = "get detail")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = VAccountLimitResult.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/account_limit/detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> getLimit(
      @RequestParam(value = "cust_id", required = false) Long custId)
      throws Exception {
    String clientIp = clientInfo.getClientIp();
    List<VAccountLimit> lsResult = accountLimitService.searchVAccountLimit(custId);
    // tra ke qua
    return responseOK(lsResult);
  }

  @ApiOperation(value = "update", tags = "update")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = VAccountLimit.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/account_limit", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      "application/json"})
  public ResponseEntity<Object> updateManage(@Valid @RequestBody AccountLimitDto accountLimitDto,
      @RequestParam(value = "cust_id") Long custId)
      throws Exception {
    String clientIp = clientInfo.getClientIp();
    log.info(">>> [save Account Limit] START <<< [clientIp] = {}", clientIp);
    accountLimitDto.setCustId(custId);
    AccountLimitDto result = accountLimitService.updateManage(accountLimitDto);
    return responseOK(result);
  }

  @ApiOperation(value = "save", tags = "save")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = AccountLimit.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/account_limit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      "application/json"})
  public ResponseEntity<Object> saveManage(@Valid @RequestBody AccountLimitDto accountLimitDto)
      throws Exception {
    String clientIp = clientInfo.getClientIp();
    log.info(">>> [save Account Limit] START <<< [clientIp] = {}", clientIp);
    AccountLimitDto result = accountLimitService.saveManage(accountLimitDto);
    return responseOK(result);
  }

  @ApiOperation(value = "delete", tags = "delete")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success"),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/account_limit", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      "application/json"})
  public ResponseEntity<Object> deleteCompany(
      @RequestBody AccountLimitDto accountLimitDto)
      throws Exception {
    String clientIp = clientInfo.getClientIp();
    log.info(">>> [delete Account limit] START <<< [clientIp] = {}", clientIp);
    // xoa bang
    AccountLimitDto result = accountLimitService.deleteManage(accountLimitDto);
    // tra gia tri
    return responseOK(result);
  }

}
