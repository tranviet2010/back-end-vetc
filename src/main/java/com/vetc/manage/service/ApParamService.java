package com.vetc.manage.service;

import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.entity.ApParam;
import com.vetc.manage.entity.CustVehicle;
import java.util.List;

public interface ApParamService {

  ListResult<ApParam> search(SearchBase searchBase) throws Exception;

  ApParam get(Long id);

  ApParam save(ApParam object) throws Exception;

  ApParam update(ApParam object, Long id) throws Exception;

  ApParam delete(Long id) throws Exception;

  List<ApParam> getAll();
}
