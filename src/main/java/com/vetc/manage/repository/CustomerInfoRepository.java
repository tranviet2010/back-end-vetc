package com.vetc.manage.repository;

import com.vetc.manage.entity.CustomerInfo;
import com.vetc.manage.jpa.GenericRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerInfoRepository extends GenericRepository<CustomerInfo, Long>,
    JpaRepository<CustomerInfo, Long> {
  List<CustomerInfo> findCustomerInfoByCusId(Long custId);

}
