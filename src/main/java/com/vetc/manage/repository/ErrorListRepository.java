package com.vetc.manage.repository;

import com.vetc.manage.entity.ErrorList;
import com.vetc.manage.jpa.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorListRepository extends GenericRepository<ErrorList, Long>, JpaRepository<ErrorList, Long> {
}
