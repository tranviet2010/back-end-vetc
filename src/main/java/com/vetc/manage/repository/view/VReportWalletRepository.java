package com.vetc.manage.repository.view;

import com.vetc.manage.entity.view.VReportWallet;
import com.vetc.manage.jpa.GenericRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VReportWalletRepository extends JpaRepository<VReportWallet, String>,
    GenericRepository<VReportWallet, String> {

}
