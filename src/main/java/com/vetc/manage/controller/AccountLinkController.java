package com.vetc.manage.controller;

import com.vetc.manage.controller.base.ControllerBase;
import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.dto.ResponseDto;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.entity.view.VAccountLink;
import com.vetc.manage.entity.view.VAccountLinkDetail;
import com.vetc.manage.service.AccountLinkService;
import com.vetc.manage.swagger.VAccountLinkResult;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Liên kết ví với ngân hàng / AccountLink")
@RequestMapping("/api")
@RestController
@Slf4j
public class AccountLinkController extends ControllerBase {

  @Autowired
  private AccountLinkService accountLinkService;


  @ApiOperation(value = "search", tags = "search")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = VAccountLinkResult.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/account_link", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> search(
      @RequestParam(value = "page", required = false) Integer pageNo,
      @RequestParam(value = "limit", required = false) Integer limit,
      @RequestParam(value = "order_field", required = false) String orderField,
      @RequestParam(value = "order_type", defaultValue = "false", required = false) boolean orderType)
      throws Exception {
    String clientIp = clientInfo.getClientIp();
    log.info(">>> [searchCompany] START <<< [clientIp] = {}", clientIp);
    // tao doi duong tim kiem co ban.
    SearchBase searchBase = buildSearchBase(pageNo, limit, orderField, orderType);
    // them cac thuoc tinh tim kiem
    addSearchField(searchBase, request.getParameterNames(), null);
    // goi ham tim kiem co ban
    ListResult<VAccountLink> lsResult = accountLinkService.searchVAccountLink(searchBase);
    // tra ke qua
    return responseOK(lsResult);
  }

  @ApiOperation(value = "get account link detail", tags = "get account link detail")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = VAccountLinkDetail.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/account_link/detail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> get(
      @RequestParam(value = "cust_id", required = true) Long custId,
      @RequestParam(value = "account_link_id", required = true) Long accountLinkId)
      throws Exception {
    String clientIp = clientInfo.getClientIp();
    log.info(">>> [get] START <<< [clientIp] = {}", clientIp);
    List<VAccountLinkDetail> result = accountLinkService.getVAccountLinkDetail(custId,
        accountLinkId);
    return responseOK(result);
  }

}
