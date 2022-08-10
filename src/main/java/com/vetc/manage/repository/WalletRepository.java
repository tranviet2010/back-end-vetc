package com.vetc.manage.repository;

import com.vetc.manage.entity.Wallet;
import com.vetc.manage.jpa.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends GenericRepository<Wallet, Long>,
    JpaRepository<Wallet, Long> {

  Wallet findByCustId(Long custId);

}
