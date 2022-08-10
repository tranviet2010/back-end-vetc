package com.vetc.manage.service;

import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.entity.view.VReportTwoTransfer;
import com.vetc.manage.entity.view.VReportWallet;
import com.vetc.manage.entity.view.VReportWalletPhase;
import java.util.Map;

public interface ReportWalletService {

  ListResult<VReportWallet> searchSum(Map<String, String> request, int pageSize,
      int pageIndex) throws Exception;

  ListResult<VReportWalletPhase> searchPhase(Map<String, String> request, int pageSize,
      int pageIndex);

  ListResult<VReportTwoTransfer> searchReportWalletTwoTransfer(Map<String, String> request,
      int pageSize,
      int pageIndex);
}
