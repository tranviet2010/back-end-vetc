package com.vetc.manage.controller;

import com.vetc.manage.service.common.CachingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {

  @Autowired
  private CachingService cachingService;

  @RequestMapping(value = "/cache/reload", method = RequestMethod.GET)
  public ResponseEntity<?> get() {
    cachingService.loadCached();
    return ResponseEntity.status(HttpStatus.OK.value()).body("reload cache done.");
  }
}
