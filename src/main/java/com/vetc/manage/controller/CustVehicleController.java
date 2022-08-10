package com.vetc.manage.controller;

import com.vetc.manage.controller.base.ControllerBase;
import com.vetc.manage.controller.dto.ResponseDto;
import com.vetc.manage.entity.CustVehicle;
import com.vetc.manage.entity.Customer;
import com.vetc.manage.service.CustVehicleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Vehicle / Biển số xe hách hàng")
@RequestMapping("/api")
@RestController
@Slf4j
public class CustVehicleController extends ControllerBase {

  @Autowired
  private CustVehicleService custVehicleService;

  @ApiOperation(value = "get all Vehicle by cust ID", tags = "get all Vehicle by cust ID")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = Customer.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @GetMapping(value = "/vehicle", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> get(
      @RequestParam(value = "customerId", required = true) Long customerId)
      throws Exception {
    String clientIp = clientInfo.getClientIp();
    log.info(">>> [get] START <<< [clientIp] = {}", clientIp);
    List<CustVehicle> result = custVehicleService.getByCustId(customerId);
    return responseOK(result);
  }
}
