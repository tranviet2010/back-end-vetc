package com.vetc.manage.repository;

import com.vetc.manage.entity.FeeTransaction;
import com.vetc.manage.jpa.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeeTransactionRepository extends GenericRepository<FeeTransaction, Long>,
    JpaRepository<FeeTransaction, Long> {

}
