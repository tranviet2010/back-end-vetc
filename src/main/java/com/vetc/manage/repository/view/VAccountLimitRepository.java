package com.vetc.manage.repository.view;

import com.vetc.manage.entity.view.VAccountLimit;
import com.vetc.manage.jpa.GenericRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VAccountLimitRepository extends GenericRepository<VAccountLimit, Long>,
    JpaRepository<VAccountLimit, Long> {

  @Query(value = "select * from  V_ACCOUNT_LIMIT v where v.CUST_ID = :custId", nativeQuery = true)
  List<VAccountLimit> getByCustId(@Param(value = "custId") Long custId);
}
