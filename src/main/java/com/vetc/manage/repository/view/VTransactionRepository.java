package com.vetc.manage.repository.view;

import com.vetc.manage.entity.view.VTransaction;
import com.vetc.manage.jpa.GenericRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VTransactionRepository extends GenericRepository<VTransaction, Long>,
    JpaRepository<VTransaction, Long> {

  @Query(value = "select v from VTransaction v where v.productCode = :code1 or v.productCode = :code2")
  Page<VTransaction> findTransactionPay
      (@Param(value = "code1") String code1,
      @Param(value = "code2") String code2,
          Pageable pageable);
}
