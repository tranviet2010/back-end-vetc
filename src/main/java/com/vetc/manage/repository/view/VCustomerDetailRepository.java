package com.vetc.manage.repository.view;

import com.vetc.manage.entity.view.VCustomer;
import com.vetc.manage.entity.view.VCustomerDetail;
import com.vetc.manage.jpa.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VCustomerDetailRepository extends GenericRepository<VCustomerDetail, Long>,
    JpaRepository<VCustomerDetail, Long> {

}
