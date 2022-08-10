package com.vetc.manage.repository;

import com.vetc.manage.entity.VerifyRuleConfig;
import com.vetc.manage.jpa.GenericRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VerifyRuleConfigRepository extends GenericRepository<VerifyRuleConfig, Long>,
    JpaRepository<VerifyRuleConfig, Long> {

  @Query(value = "select v from VerifyRuleConfig v where v.custId =:custId and v.verifyMethod =:method ")
  VerifyRuleConfig findByCustId(@Param(value = "custId") Long custId,
      @Param(value = "method") String method);

  @Query(value = "select v from VerifyRuleConfig v where v.custType =:custType and v.verifyMethod =:method ")
  VerifyRuleConfig findByType(@Param(value = "custType") String custType,
      @Param(value = "method") String method);

  @Query(value = "select v from VerifyRuleConfig v where v.custGroupCode =:group and v.verifyMethod =:method ")
  VerifyRuleConfig findByGroup(@Param(value = "group") String group,
      @Param(value = "method") String method);


  List<VerifyRuleConfig> findByCustGroupCode(String custGroup);

  List<VerifyRuleConfig> findAllByCustGroupCodeIn(List<String> lsCode);

  List<VerifyRuleConfig> findByCustType(String custType);

}
