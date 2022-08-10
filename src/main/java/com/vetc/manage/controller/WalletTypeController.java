package com.vetc.manage.controller;

import com.vetc.manage.controller.base.ControllerBase;
import com.vetc.manage.controller.dto.ResponseDto;
import com.vetc.manage.entity.WalletType;
import com.vetc.manage.service.WalletTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Kiểu ví / WalletType")
@RequestMapping("/api")
@RestController
@Slf4j
public class WalletTypeController extends ControllerBase {

  @Autowired
  private WalletTypeService walletTypeService;

  @ApiOperation(value = "get all list wallet type", tags = "get all list wallet type")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = WalletType.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/wallet-type", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> get()
      throws Exception {
    String clientIp = clientInfo.getClientIp();
    List<WalletType> lsResult = walletTypeService.getAll();
    return responseOK(lsResult);
  }
}
