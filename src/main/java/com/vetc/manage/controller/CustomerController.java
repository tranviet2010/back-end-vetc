package com.vetc.manage.controller;

import com.vetc.manage.controller.base.ControllerBase;
import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.dto.ResponseDto;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.dto.request.CustomerLimitDto;
import com.vetc.manage.entity.Customer;
import com.vetc.manage.entity.view.VCustomer;
import com.vetc.manage.entity.view.VCustomerDetail;
import com.vetc.manage.service.CustomerService;
import com.vetc.manage.swagger.CustomerResult;
import com.vetc.manage.swagger.VCustomerResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.Map;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author HaNQ
 */

@Api(tags = "Customer / Khách hàng")
@RequestMapping("/api")
@RestController
@Slf4j
public class CustomerController extends ControllerBase {

  @Autowired
  private CustomerService customerService;

  @ApiOperation(value = "search customer", tags = "search customer")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = CustomerResult.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @GetMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> search(
      @RequestParam(value = "page", required = false) Integer pageNo,
      @RequestParam(value = "limit", required = false) Integer limit,
      @RequestParam(value = "order_field", required = false) String orderField,
      @RequestParam(value = "order_type", defaultValue = "false", required = false) boolean orderType)
      throws Exception {
    String clientIp = clientInfo.getClientIp();
    log.info(">>> [search] START <<< [clientIp] = {}", clientIp);
    // tao doi duong tim kiem co ban.
    SearchBase searchBase = buildSearchBase(pageNo, limit, orderField, orderType);
    // them cac thuoc tinh tim kiem
    addSearchField(searchBase, request.getParameterNames(), null);
    // goi ham tim kiem co ban
    ListResult<Customer> lsResult = customerService.search(searchBase);
    // tra ke qua
    return responseOK(lsResult);
  }

  @ApiOperation(value = "search customer view", tags = "search customer view")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = VCustomerResult.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @GetMapping(value = "/customer-views", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> searchView(
      @RequestParam(value = "page", required = false) Integer pageNo,
      @RequestParam(value = "limit", required = false) Integer limit,
      @RequestParam(value = "order_field", required = false) String orderField,
      @RequestParam(value = "order_type", defaultValue = "false", required = false) boolean orderType)
      throws Exception {
    String clientIp = clientInfo.getClientIp();
    log.info(">>> [search] START <<< [clientIp] = {}", clientIp);
    // tao doi duong tim kiem co ban.
    SearchBase searchBase = buildSearchBase(pageNo, limit, orderField, orderType);
    // them cac thuoc tinh tim kiem
    addSearchField(searchBase, request.getParameterNames(), null);
    // goi ham tim kiem co ban
    ListResult<VCustomer> lsResult = customerService.searchview(searchBase);
    // tra ke qua
    return responseOK(lsResult);
  }
  @ApiOperation(value = "search customer view time", tags = "search customer view time")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = VCustomerResult.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @GetMapping(value = "/customer-views/time", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> searchViewTime(
      @RequestParam(value = "page", defaultValue = "1", required = false) Integer pageNo,
      @RequestParam(value = "limit", defaultValue = "10", required = false) Integer limit,
      @ApiParam(value = "Tim kiem tgian: ?between:value1,value2", example = "/customer-views/time?createDate=between:02-8-2022,03-08-2022")
      @RequestParam Map<String, String> request)
      throws Exception {
    String clientIp = clientInfo.getClientIp();
    log.info(">>> [search] START <<< [clientIp] = {}", clientIp);
    ListResult<VCustomer> lsResult = customerService.searchViewTime(request, limit, pageNo);
    // tra ke qua
    return responseOK(lsResult);
  }

  @ApiOperation(value = "get detail customer", tags = "get detail customer")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = VCustomerDetail.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @GetMapping(value = "/customer-detail", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> getDetail(
      @RequestParam(value = "customerId", required = true) Long customerId)
      throws Exception {
    String clientIp = clientInfo.getClientIp();
    log.info(">>> [get] START <<< [clientIp] = {}", clientIp);
    VCustomerDetail result = customerService.getDetail(customerId);
    return responseOK(result);
  }

  @ApiOperation(value = "get one", tags = "get one")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = Customer.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @GetMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> get(
      @RequestParam(value = "customerId", required = true) Long customerId)
      throws Exception {
    String clientIp = clientInfo.getClientIp();
    log.info(">>> [get] START <<< [clientIp] = {}", clientIp);
    Customer result = customerService.get(customerId);
    return responseOK(result);
  }

  @ApiOperation(value = "save customer", tags = "save customer")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = Customer.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @PostMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      "application/json"})
  public ResponseEntity<Object> save(@Valid @RequestBody Customer customer) throws Exception {
    String clientIp = clientInfo.getClientIp();
    log.info(">>> [save] START <<< [clientIp] = {}", clientIp);
    Customer result = customerService.save(customer);
    return responseOK(result);
  }

  @ApiOperation(value = "update customer", tags = "update customer")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = Customer.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @PutMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      "application/json"})
  public ResponseEntity<Object> update(@Valid @RequestBody CustomerLimitDto customer,
      @RequestParam(value = "customerId", required = true) Long customerId
      ) throws Exception {
    String clientIp = clientInfo.getClientIp();
    log.info(">>> [update] START <<< [clientIp] = {}", clientIp);
    Customer result = customerService.updateManage(customer, customerId);
    return responseOK(result);
  }

  @ApiOperation(value = "delete customer", tags = "delete customer")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = Customer.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @DeleteMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> delete(
      @RequestParam(value = "customerId", required = true) Long customerId)
      throws Exception {
    String clientIp = clientInfo.getClientIp();
    log.info(">>> [delete] START <<< [clientIp] = {}", clientIp);
    // xoa bang
    Customer customer = customerService.delete(customerId);
    // tra gia tri
    return responseOK(customer);
  }

  @ApiOperation(value = "update state customer ", tags = "update state customer ")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = Customer.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/customer/state", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> updateState(
      @RequestParam(value = "customerId", required = true) Long customerId,
      @RequestParam(value = "value", required = true) String value) throws Exception {
    String clientIp = clientInfo.getClientIp();
    log.info(">>> [save] START <<< [clientIp] = {}", clientIp);
    Customer result = customerService.updateState(customerId, value);
    return responseOK(result);
  }

  @ApiOperation(value = "update verify state customer ", tags = "update verify state customer ")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = Customer.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/customer/verify-state", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> updateVerifyState(
      @RequestParam(value = "customerId", required = true) Long customerId,
      @RequestParam(value = "value", required = true) String value) throws Exception {
    String clientIp = clientInfo.getClientIp();
    log.info(">>> [save] START <<< [clientIp] = {}", clientIp);
    Customer result = customerService.updateVerifyState(customerId, value);
    return responseOK(result);
  }

}
