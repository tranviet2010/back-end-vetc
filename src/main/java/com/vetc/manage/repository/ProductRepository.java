package com.vetc.manage.repository;

import com.vetc.manage.entity.Product;
import com.vetc.manage.jpa.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends GenericRepository<Product, Long>,
    JpaRepository<Product, Long> {


}
