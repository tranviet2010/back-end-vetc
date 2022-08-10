package com.vetc.manage.repository;

import com.vetc.manage.entity.CustVehicle;
import com.vetc.manage.jpa.GenericRepository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustVehicleRepository extends GenericRepository<CustVehicle, Long>,
    JpaRepository<CustVehicle, Long> {

//  @Query(value = "SELECT C FROM CustVehicle C WHERE C.custId = :custIds")
  List<CustVehicle> findAllByCustId(Long custId);

}
