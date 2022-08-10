package com.vetc.manage.controller;

import com.vetc.manage.controller.base.ControllerBase;
import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.dto.ResponseDto;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.entity.Product;
import com.vetc.manage.service.ProductService;
import com.vetc.manage.swagger.ProductResult;
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

@Api(tags = "Product / Loai giao dich - Sản phẩm")
@RequestMapping("/api")
@RestController
@Slf4j
public class ProductController extends ControllerBase {

  @Autowired
  private ProductService productService;


  @ApiOperation(value = "search", tags = "search")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = ProductResult.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
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
    ListResult<Product> lsResult = productService.search(searchBase);
    // tra ke qua
    return responseOK(lsResult);

  }

  @ApiOperation(value = "get all", tags = "get all")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = Product.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/product/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> getAll()
      throws Exception {
    List<Product> lsResult = productService.getAll();
    // tra ke qua
    return responseOK(lsResult);
  }

  @ApiOperation(value = "get", tags = "get")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = Product.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/product", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> get(
      @RequestParam(value = "product_ id", required = true) Long productId)
      throws Exception {
    String clientIp = clientInfo.getClientIp();
    log.info(">>> [get] START <<< [clientIp] = {}", clientIp);
    Product result = productService.get(productId);
    return responseOK(result);
  }

  @ApiOperation(value = "save", tags = "save")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = Product.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/product", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      "application/json"})
  public ResponseEntity<Object> save(@RequestBody Product product) throws Exception {
    String clientIp = clientInfo.getClientIp();
    log.info(">>> [saveap] START <<< [clientIp] = {}", clientIp);
    Product result = productService.save(product);
    return responseOK(result);
  }

  @ApiOperation(value = "update", tags = "update")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = Product.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/product", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      "application/json"})
  public ResponseEntity<Object> update(@RequestBody Product product) throws Exception {
    String clientIp = clientInfo.getClientIp();
    log.info(">>> [update] START <<< [clientIp] = {}", clientIp);
    Product result = productService.save(product);
    return responseOK(result);
  }

  @ApiOperation(value = "delete", tags = "delete")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success", response = Product.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/product", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      MediaType.ALL_VALUE})
  public ResponseEntity<Object> delete(
      @RequestParam(value = "product_id", required = true) Long productId)
      throws Exception {
    String clientIp = clientInfo.getClientIp();
    log.info(">>> [delete] START <<< [clientIp] = {}", clientIp);
    // xoa bang
    Product existed = productService.delete(productId);
    // tra gia tri
    return responseOK(existed);
  }
}
