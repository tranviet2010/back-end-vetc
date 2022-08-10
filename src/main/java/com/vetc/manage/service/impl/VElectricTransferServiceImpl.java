package com.vetc.manage.service.impl;

import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.entity.view.VElectricTransfer;
import com.vetc.manage.repository.view.VElectricTransferRepository;
import com.vetc.manage.service.VElectricTransferService;
import com.vetc.manage.service.common.ServiceBase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VElectricTransferServiceImpl
        extends ServiceBase<VElectricTransfer, Long, VElectricTransferRepository>
        implements VElectricTransferService {


    @Override
    public ListResult<VElectricTransfer> search(SearchBase searchBase) throws Exception {
        return super.search(searchBase);
    }

    @Override
    public VElectricTransfer get(Long id) {
        return super.get(id);
    }


    @Override
    public VElectricTransfer save(VElectricTransfer object) throws Exception {
        return super.save(object);
    }

    @Override
    public VElectricTransfer update(VElectricTransfer object, Long id) throws Exception {
        return super.update(object, id);
    }

    @Override
    public VElectricTransfer delete(Long id) throws Exception {
        return super.delete(id);
    }

    @Override
    public List<VElectricTransfer> getAll() {
        return super.getAll();
    }
}
