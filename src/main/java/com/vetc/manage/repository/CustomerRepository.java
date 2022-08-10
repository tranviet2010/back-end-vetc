package com.vetc.manage.repository;

import com.vetc.manage.entity.Customer;
import com.vetc.manage.jpa.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends GenericRepository<Customer, Long>,
    JpaRepository<Customer, Long> {

}
