package com.vetc.manage.service;

import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.entity.CustVehicle;
import java.util.List;

public interface CustVehicleService {

  ListResult<CustVehicle> search(SearchBase searchBase) throws Exception;

  CustVehicle get(Long id);

  List<CustVehicle> getByCustId(Long id);

  CustVehicle save(CustVehicle object) throws Exception;

  CustVehicle update(CustVehicle object, Long id) throws Exception;

   CustVehicle delete(Long id) throws Exception;

  List<CustVehicle> getAll();
}
