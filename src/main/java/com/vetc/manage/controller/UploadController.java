package com.vetc.manage.controller;

import com.vetc.manage.controller.base.ControllerBase;
import com.vetc.manage.controller.dto.ResponseDto;
import com.vetc.manage.service.common.UploadService;
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
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author HungVM
 */

@Api(tags = "UpLoad File")
@RequestMapping("/api")
@RestController
@Slf4j

public class UploadController extends ControllerBase {

  @Autowired
  private UploadService uploadService;

  @ApiOperation(value = "upload", tags = "upload")
  @ApiResponses(value = {@ApiResponse(code = 200, message = "Success", response = List.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = ResponseDto.class),
      @ApiResponse(code = 400, message = "Bad Request", response = ResponseDto.class)})
  @RequestMapping(value = "/images", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = {
      "multipart/form-data"})
  public ResponseEntity<Object> images(
      @RequestParam(value = "member_id", required = false) Long memberId,
//      @RequestParam(value = "type", required = false) String type,
      @RequestParam(value = "files", required = true) MultipartFile[] files) {
    List<String> lstRs = uploadService.uploadImage(memberId, files);
    return responseOK(lstRs);
  }
}
