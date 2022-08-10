package com.vetc.manage.repository;

import com.vetc.manage.entity.CustomerGroup;
import com.vetc.manage.jpa.GenericRepository;
import java.security.acl.Group;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerGroupRepository extends GenericRepository<CustomerGroup, Long>,
    JpaRepository<CustomerGroup, Long> {

  List<CustomerGroup> findAllByIdIn(List<Long> groupIds);
}
