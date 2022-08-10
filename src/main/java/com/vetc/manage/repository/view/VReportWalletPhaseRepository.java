package com.vetc.manage.repository.view;


import com.vetc.manage.entity.view.VReportWalletPhase;
import com.vetc.manage.jpa.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VReportWalletPhaseRepository extends GenericRepository<VReportWalletPhase, String>,
    JpaRepository<VReportWalletPhase, String> {

}
