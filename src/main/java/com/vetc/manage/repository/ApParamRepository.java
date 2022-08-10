package com.vetc.manage.repository;

import com.vetc.manage.entity.ApParam;
import com.vetc.manage.jpa.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApParamRepository extends GenericRepository<ApParam, Long>,
    JpaRepository<ApParam, Long> {


}
