package com.vetc.manage.controller;

import com.vetc.manage.controller.base.ControllerBase;
import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.dto.ResponseDto;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.entity.ApParam;
import com.vetc.manage.entity.view.VElectricTransfer;
import com.vetc.manage.service.ApParamService;
import com.vetc.manage.service.VElectricTransferService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Electric Transfer / Hỗ trợ chuyển tiền điện tử")
@RequestMapping("/api")
@RestController
@Slf4j
public class ElectricTranferController extends ControllerBase {

  @Autowired
  private VElectricTransferService vElectricTransferService;


  @ApiOperation(value = "search ApParam", tags = "search ApParam")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = ApParam.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/ap_params", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> search(
      @RequestParam(value = "page", required = false) Integer pageNo,
      @RequestParam(value = "limit", required = false) Integer limit,
      @RequestParam(value = "order_field", required = false) String orderField,
      @RequestParam(value = "order_type", defaultValue = "false", required = false) boolean orderType)
      throws Exception {
    String clientIp = clientInfo.getClientIp();
    log.info(">>> [search apParam] START <<< [clientIp] = {}", clientIp);
    // tao doi duong tim kiem co ban.
    SearchBase searchBase = buildSearchBase(pageNo, limit, orderField, orderType);
    // them cac thuoc tinh tim kiem
    addSearchField(searchBase, request.getParameterNames(), null);
    // goi ham tim kiem co ban
    ListResult<VElectricTransfer> lsResult = vElectricTransferService.search(searchBase);
    // tra ke qua
    return responseOK(lsResult);

  }

  @ApiOperation(value = "get all ApParam", tags = "get all ApParam")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = ApParam.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/ap_param/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> getAll()
      throws Exception {
    List<ApParam> lsResult = apParamService.getAll();
    // tra ke qua
    return responseOK(lsResult);
  }

  @ApiOperation(value = "get ApParam", tags = "get ApParam")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = ApParam.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/ap_param", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> get(
      @RequestParam(value = "apparam_id", required = true) Long apParamId)
      throws Exception {
    String clientIp = clientInfo.getClientIp();
    log.info(">>> [get apParam] START <<< [clientIp] = {}", clientIp);
    ApParam result = apParamService.get(apParamId);
    return responseOK(result);
  }

  @ApiOperation(value = "save ApParam", tags = "save ApParam")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = ApParam.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/ap_param", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      "application/json"})
  public ResponseEntity<Object> save(@Valid @RequestBody ApParam apParam) throws Exception {
    String clientIp = clientInfo.getClientIp();
    log.info(">>> [saveapParam] START <<< [clientIp] = {}", clientIp);
    ApParam result = apParamService.save(apParam);
    return responseOK(result);
  }

  @ApiOperation(value = "update ApParam", tags = "update ApParam")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = ApParam.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/ap_param", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      "application/json"})
  public ResponseEntity<Object> update(@Valid @RequestBody ApParam apParam,
      @RequestParam(value = "apparam_id", required = true) Long apParamId) throws Exception {
    String clientIp = clientInfo.getClientIp();
    log.info(">>> [update apParam] START <<< [clientIp] = {}", clientIp);
    apParam.setId(apParamId);
    ApParam result = apParamService.save(apParam);
    return responseOK(result);
  }

  @ApiOperation(value = "delete ApParam", tags = "delete ApParam")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = ApParam.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/ap_param", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> delete(
      @RequestParam(value = "app_param_id", required = true) Long appParamId)
      throws Exception {
    String clientIp = clientInfo.getClientIp();
    log.info(">>> [delete apParam] START <<< [clientIp] = {}", clientIp);
    // xoa bang
    ApParam existed = apParamService.delete(appParamId);
    // tra gia tri
    return responseOK(existed);
  }
}
