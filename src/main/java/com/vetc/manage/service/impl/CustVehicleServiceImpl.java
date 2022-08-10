package com.vetc.manage.service.impl;

import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.entity.CustVehicle;
import com.vetc.manage.enums.ApParamEnum;
import com.vetc.manage.repository.CustVehicleRepository;
import com.vetc.manage.service.CustVehicleService;
import com.vetc.manage.service.common.CachingService;
import com.vetc.manage.service.common.ServiceBase;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustVehicleServiceImpl extends ServiceBase<CustVehicle, Long, CustVehicleRepository>
    implements CustVehicleService {

  @Autowired
  private CustVehicleRepository custVehicleRepository;

  @Autowired
  private CachingService cachingService;

  @Override
  public ListResult<CustVehicle> search(SearchBase searchBase) throws Exception {
    return super.search(searchBase);
  }

  @Override
  public CustVehicle get(Long id) {
    return super.get(id);
  }

  @Override
  public List<CustVehicle> getByCustId(Long id) {
    List<CustVehicle> lsResult = custVehicleRepository.findAllByCustId(id);
    try {
      lsResult.stream().filter(item -> item.getVehicleType() != null).forEach(item -> {
        String ref = cachingService.getApParam(ApParamEnum.VEHICLE.getValue(), item.getVehicleType());
         item.setVehicleTypeRef(ref);
      });
    } catch (Exception ex) {

    }
    return lsResult;
  }

  @Override
  public CustVehicle save(CustVehicle object) throws Exception {
    return super.save(object);
  }

  @Override
  public CustVehicle update(CustVehicle object, Long id) throws Exception {
    return super.update(object, id);
  }

  @Override
  public CustVehicle delete(Long id) throws Exception {
    return super.delete(id);
  }

  @Override
  public List<CustVehicle> getAll() {
    return super.getAll();
  }
}
