package com.vetc.manage.controller;

import com.vetc.manage.controller.base.ControllerBase;
import com.vetc.manage.controller.dto.ResponseDto;
import com.vetc.manage.dto.request.VerifyRuleConfigDto;
import com.vetc.manage.dto.request.WalletDto;
import com.vetc.manage.entity.VerifyRuleConfig;
import com.vetc.manage.entity.Wallet;
import com.vetc.manage.service.VerifyRuleConfigService;
import com.vetc.manage.service.WalletService;
import com.vetc.manage.swagger.VerifyRuleConfigResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Ví / Wallet")
@RequestMapping("/api")
@RestController
@Slf4j
public class WalletController extends ControllerBase {

  @Autowired
  private WalletService walletService;


//  @ApiOperation(value = "get verify rule config", tags = "get verify rule config")
//  @ApiResponses(value = {
//      @ApiResponse(code = 200, message = "Success", response = VerifyRuleConfigResult.class),
//      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
//      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
//  @RequestMapping(value = "/verify-rule-config", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
//      MediaType.ALL_VALUE})
//  public ResponseEntity<Object> get(
//      @RequestParam(value = "custId", required = false) Long custId)
//      throws Exception {
//    String clientIp = clientInfo.getClientIp();
//    List<VerifyRuleConfig> lsResult = verifyRuleConfigService.getVerifyRuleConfigByCust(custId);
//    return responseOK(lsResult);
//  }

  @ApiOperation(value = "save wallet(ví)", tags = "save wallet(ví)")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = WalletDto.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/wallet", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      "application/json"})
  public ResponseEntity<Object> save(
      @RequestBody WalletDto walletDto)
      throws Exception {
    String clientIp = clientInfo.getClientIp();
    Wallet result = walletService.saveWalletAndCustomerAndCustomerInfo(walletDto);
    return responseOK(result);
  }

//  @ApiOperation(value = "update verify rule config (cấu hình xác thực)", tags = "Update verify rule config (cấu hình xác thực)")
//  @ApiResponses(value = {
//      @ApiResponse(code = 200, message = "Success", response = VerifyRuleConfigDto.class),
//      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
//      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
//  @RequestMapping(value = "/verify-rule-config", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
//      "application/json"})
//  public ResponseEntity<Object> update(
//      @RequestBody VerifyRuleConfig verifyRuleConfig)
//      throws Exception {
//    String clientIp = clientInfo.getClientIp();
//    VerifyRuleConfig result = verifyRuleConfigService.update(verifyRuleConfig,
//        verifyRuleConfig.getId());
//    return responseOK(result);
//  }
//
//  @ApiOperation(value = "delete verify rule config (cấu hình xác thực)", tags = "delete verify rule config (cấu hình xác thực)")
//  @ApiResponses(value = {
//      @ApiResponse(code = 200, message = "Success", response = VerifyRuleConfig.class),
//      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
//      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
//  @RequestMapping(value = "/verify-rule-confige", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
//      MediaType.ALL_VALUE})
//  public ResponseEntity<Object> delete(
//      @RequestParam(value = "verify_rule_config_id", required = true) Long verifyRuleConfigId)
//      throws Exception {
//    String clientIp = clientInfo.getClientIp();
//    VerifyRuleConfig result = verifyRuleConfigService.delete(verifyRuleConfigId);
//    return responseOK(result);
//  }
//

}
