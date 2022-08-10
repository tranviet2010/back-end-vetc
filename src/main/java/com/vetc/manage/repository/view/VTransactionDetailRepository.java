package com.vetc.manage.repository.view;

import com.vetc.manage.entity.view.VTransactionDetail;
import com.vetc.manage.jpa.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VTransactionDetailRepository extends
    GenericRepository<VTransactionDetail, Long>,
    JpaRepository<VTransactionDetail, Long> {

  VTransactionDetail getVTransactionDetailByTransIdAndProductCode(Long transId, String productCode);

}
