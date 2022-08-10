package com.vetc.manage.repository;

import com.vetc.manage.entity.WalletType;
import com.vetc.manage.jpa.GenericRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletTypeRepository extends GenericRepository<WalletType, Long>,
    JpaRepository<WalletType, Long> {

  @Query(value = "SELECT N from WalletType N")
  List<WalletType> getWalletTypeNote();


}
