package com.vetc.manage.service.impl;

import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.entity.WalletType;
import com.vetc.manage.repository.WalletTypeRepository;
import com.vetc.manage.service.WalletTypeService;
import com.vetc.manage.service.common.ServiceBase;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class WalletTypeServiceImpl
    extends ServiceBase<WalletType, Long, WalletTypeRepository>
    implements WalletTypeService {


  @Override
  public ListResult<WalletType> search(SearchBase searchBase) throws Exception {
    return super.search(searchBase);
  }

  @Override
  public WalletType get(Long id) {
    return super.get(id);
  }


  @Override
  public WalletType save(WalletType object) throws Exception {
    return super.save(object);
  }

  @Override
  public WalletType update(WalletType object, Long id) throws Exception {
    return super.update(object, id);
  }

  @Override
  public WalletType delete(Long id) throws Exception {
    return super.delete(id);
  }

  @Override
  public List<WalletType> getAll() {
    return super.getAll();
  }
}
