package com.vetc.manage.repository.view;

import com.vetc.manage.entity.view.VElectricTransfer;
import com.vetc.manage.jpa.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VElectricTransferRepository extends GenericRepository<VElectricTransfer, Long>, JpaRepository<VElectricTransfer, Long> {
}
