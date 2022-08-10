package com.vetc.manage.service.impl;

import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.entity.ApParam;
import com.vetc.manage.repository.ApParamRepository;
import com.vetc.manage.service.ApParamService;
import com.vetc.manage.service.common.ServiceBase;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ApParamServiceImpl extends ServiceBase<ApParam, Long, ApParamRepository>
    implements ApParamService {


  @Override
  public ListResult<ApParam> search(SearchBase searchBase) throws Exception {
    return super.search(searchBase);
  }

  @Override
  public ApParam get(Long id) {
    return super.get(id);
  }


  @Override
  public ApParam save(ApParam object) throws Exception {
    return super.save(object);
  }

  @Override
  public ApParam update(ApParam object, Long id) throws Exception {
    return super.update(object, id);
  }

  @Override
  public ApParam delete(Long id) throws Exception {
    return super.delete(id);
  }

  @Override
  public List<ApParam> getAll() {
    return super.getAll();
  }
}
