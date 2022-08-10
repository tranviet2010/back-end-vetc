
package com.vetc.manage.repository;

import com.vetc.manage.entity.CustGroupMap;
import com.vetc.manage.jpa.GenericRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustGroupMapRepository extends GenericRepository<CustGroupMap, Long>,
    JpaRepository<CustGroupMap, Long> {

List<CustGroupMap> findByCustId(Long custId);
}
