package com.vetc.manage.service.impl;

import com.vetc.manage.common.SetPredicate;
import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.entity.view.VReportTwoTransfer;
import com.vetc.manage.entity.view.VReportWallet;
import com.vetc.manage.entity.view.VReportWalletPhase;
import com.vetc.manage.repository.view.VReportTwoTransferRepository;
import com.vetc.manage.service.ReportWalletService;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportWalletServiceImpl implements ReportWalletService {

  @Autowired
  private EntityManager entityManager;
  @Autowired
  private SetPredicate setPredicate;

  @Autowired
  private VReportTwoTransferRepository vReportTwoTransferRepository;

  @Override
  public ListResult<VReportWallet> searchSum(Map<String, String> request, int pageSize,
      int pageIndex) throws Exception {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<VReportWallet> query = cb.createQuery(VReportWallet.class);
    Root<VReportWallet> root = query.from(VReportWallet.class);

    List<Predicate> predicates = setPredicate.setPredicates(cb, root, request);

//    if (predicates == null) throw new ParamsException(EnumCodeResponse.FILED_DOES_NOT_EXIST);

    query.select(root).where(predicates.toArray(new Predicate[]{}));
    TypedQuery<VReportWallet> typedQuery = entityManager.createQuery(query);
    int totalCount = typedQuery.getResultList().size();
    typedQuery.setFirstResult((pageIndex - 1) * pageSize);
    typedQuery.setMaxResults(pageSize);
    List<VReportWallet> lists = typedQuery.getResultList();
    ListResult<VReportWallet> result = new ListResult<>();
    result.setData(lists);
    result.setPageSize(pageSize);
    result.setPageIndex(pageIndex);
    result.setTotalCount(totalCount);
    return result;
  }

  @Override
  public ListResult<VReportWalletPhase> searchPhase(Map<String, String> request, int pageSize,
      int pageIndex) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<VReportWalletPhase> query = cb.createQuery(VReportWalletPhase.class);
    Root<VReportWalletPhase> root = query.from(VReportWalletPhase.class);

    List<Predicate> predicates = setPredicate.setPredicates(cb, root, request);

//    if (predicates == null) throw new ParamsException(EnumCodeResponse.FILED_DOES_NOT_EXIST);

    query.select(root).where(predicates.toArray(new Predicate[]{}));
    TypedQuery<VReportWalletPhase> typedQuery = entityManager.createQuery(query);
    int totalCount = typedQuery.getResultList().size();
    typedQuery.setFirstResult((pageIndex - 1) * pageSize);
    typedQuery.setMaxResults(pageSize);
    List<VReportWalletPhase> lists = typedQuery.getResultList();
    ListResult<VReportWalletPhase> result = new ListResult<>();
    result.setData(lists);
    result.setPageSize(pageSize);
    result.setPageIndex(pageIndex);
    result.setTotalCount(totalCount);
    return result;
  }

  @Override
  public ListResult<VReportTwoTransfer> searchReportWalletTwoTransfer(Map<String, String> request,
      int pageSize,
      int pageIndex) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<VReportTwoTransfer> query = cb.createQuery(VReportTwoTransfer.class);
    Root<VReportTwoTransfer> root = query.from(VReportTwoTransfer.class);

    List<Predicate> predicates = setPredicate.setPredicates(cb, root, request);

//    if (predicates == null) throw new ParamsException(EnumCodeResponse.FILED_DOES_NOT_EXIST);

    query.select(root).where(predicates.toArray(new Predicate[]{}));
    TypedQuery<VReportTwoTransfer> typedQuery = entityManager.createQuery(query);
    int totalCount = typedQuery.getResultList().size();
    typedQuery.setFirstResult((pageIndex - 1) * pageSize);
    typedQuery.setMaxResults(pageSize);
    List<VReportTwoTransfer> lists = typedQuery.getResultList();
    lists.stream().filter(item -> !Objects.isNull(item)).forEach(item -> {
      if(item.getSumDeposit() != null && item.getFeeDeposit() !=null)
      item.setSumDepositTransfer(item.getSumDeposit() + item.getFeeDeposit());
      if(item.getSumWithdraw() != null && item.getFeeWithdraw() != null)
      item.setSumWithDrawTransfer(item.getSumWithdraw() + item.getFeeWithdraw());
      if(item.getSumDepositTransfer() != null && item.getSumWithDrawTransfer() != null)
      item.setDebtDifference(item.getSumWithDrawTransfer() - item.getSumDepositTransfer());
        }
    );
    ListResult<VReportTwoTransfer> result = new ListResult<>();
    result.setData(lists);
    result.setPageSize(pageSize);
    result.setPageIndex(pageIndex);
    result.setTotalCount(totalCount);
    return result;
  }
}
