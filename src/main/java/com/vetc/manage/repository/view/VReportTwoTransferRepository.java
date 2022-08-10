package com.vetc.manage.repository.view;

import com.vetc.manage.entity.view.VReportTwoTransfer;
import com.vetc.manage.jpa.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VReportTwoTransferRepository extends GenericRepository<VReportTwoTransfer, String>,
    JpaRepository<VReportTwoTransfer, String> {

}
