package com.vetc.manage.repository.view;

import com.vetc.manage.entity.view.VAccountLinkDetail;
import com.vetc.manage.jpa.GenericRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VAccountLinkDetailRepository extends GenericRepository<VAccountLinkDetail, Long>,
    JpaRepository<VAccountLinkDetail, Long> {

  @Query(value = "select ald from VAccountLinkDetail ald where ald.custId = :custId and ald.codeTrans = :codeTrans")
  List<VAccountLinkDetail> getListAccountLinkDetail(
      @Param(value = "custId") Long custId,
      @Param(value = "codeTrans") Long codeTrans);

}
