package com.vetc.manage.service;

import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.entity.view.VElectricTransfer;

import java.util.List;

public interface VElectricTransferService {
    ListResult<VElectricTransfer> search(SearchBase searchBase) throws Exception;

    VElectricTransfer get(Long id);

    VElectricTransfer save(VElectricTransfer object) throws Exception;

    VElectricTransfer update(VElectricTransfer object, Long id) throws Exception;

    VElectricTransfer delete(Long id) throws Exception;

    List<VElectricTransfer> getAll();
}
