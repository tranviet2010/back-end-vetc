package com.vetc.manage.repository;

import com.vetc.manage.entity.AccountLimit;
import com.vetc.manage.jpa.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountLimitRepository extends GenericRepository<AccountLimit, Long>,
    JpaRepository<AccountLimit, Long> {

  @Query(value = "SELECT a FROM AccountLimit a WHERE a.productId =:productId AND a.channel =:channel AND a.custId =:custId AND a.limitType =:type")
  AccountLimit getAccountLimitByMultiField(
      @Param(value = "productId") Long productId,
      @Param(value = "channel") String channel,
      @Param(value = "custId") Long custId,
      @Param(value = "type") String type);
}
