package com.vetc.manage.service.impl;

import com.vetc.manage.common.SetPredicate;
import com.vetc.manage.enums.ApParamEnum;
import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.dto.request.CustomerLimitDto;
import com.vetc.manage.entity.Customer;
import com.vetc.manage.entity.CustomerInfo;
import com.vetc.manage.entity.view.VCustomer;
import com.vetc.manage.entity.view.VCustomerDetail;
import com.vetc.manage.enums.ExceptionEnum;
import com.vetc.manage.exception.ValidationException;
import com.vetc.manage.repository.CustomerInfoRepository;
import com.vetc.manage.repository.CustomerRepository;
import com.vetc.manage.repository.view.VCustomerDetailRepository;
import com.vetc.manage.repository.view.VCustomerRepository;
import com.vetc.manage.service.CustomerService;
import com.vetc.manage.service.common.CachingService;
import com.vetc.manage.service.common.ServiceBase;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl extends ServiceBase<Customer, Long, CustomerRepository>
    implements CustomerService {

  @Autowired
  private VCustomerRepository vCustomerRepository;

  @Autowired
  private VCustomerDetailRepository vCustomerDetailRepository;

  @Autowired
  private CustomerInfoRepository customerInfoRepository;

  @Autowired
  private SetPredicate setPredicate;

  @Autowired
  private CachingService cachingService;

  @Value("${server.customer-info.file-type.cmnd-back}")
  private String idNoBack;
  @Value("${server.customer-info.file-type.cmnd-font}")
  private String idNoFont;

  @Override
  public VCustomerDetail getDetail(Long id) throws Exception {
    VCustomerDetail result = vCustomerDetailRepository.findById(id).orElse(null);
    if (result == null) {
      throw new ValidationException(ExceptionEnum.RECORD_NOT_FOUND.getCode(),
          "Không tìm thấy khách hàng");
    }
    List<CustomerInfo> customerInfoList =
        customerInfoRepository.findCustomerInfoByCusId(result.getCustId());
    if (!customerInfoList.isEmpty()) {
      Map<String, CustomerInfo> getProducts = customerInfoList.stream()
          .collect(Collectors.toMap(CustomerInfo::getFileType, Function.identity()));
      try {
        result.setIdNoBack(getProducts.get(idNoBack).getUrl());
        result.setIdNoBack(getProducts.get(idNoFont).getUrl());
      } catch (Exception ex) {
        return result;
      }
    }

    return result;
  }

  @Override
  public ListResult<Customer> search(SearchBase searchBase) throws Exception {
    return super.search(searchBase);
  }

  @Override
  public ListResult<VCustomer> searchview(SearchBase searchBase) throws Exception {
    ListResult<VCustomer> lsResult = vCustomerRepository.search(searchBase);
    try {
      lsResult.getData().stream().filter(item -> item.getState() != null).forEach(item -> {
        String ref = cachingService.getApParam(ApParamEnum.CUSTOMER_STATE.getValue(), item.getState());
        item.setStateRef(ref);
      });
    } catch (Exception ex) {

    }
    return lsResult;
  }

  @Override
  public ListResult<VCustomer> searchViewTime(Map<String, String> request, int pageSize, int pageIndex) throws Exception {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<VCustomer> query = cb.createQuery(VCustomer.class);
    Root<VCustomer> root = query.from(VCustomer.class);

    List<Predicate> predicates = setPredicate.setPredicates(cb, root, request);

//    if (predicates == null) throw new ParamsException(EnumCodeResponse.FILED_DOES_NOT_EXIST);

    query.select(root).where(predicates.toArray(new Predicate[]{}));
    TypedQuery<VCustomer> typedQuery = entityManager.createQuery(query);
    int totalCount = typedQuery.getResultList().size();
    typedQuery.setFirstResult((pageIndex - 1) * pageSize);
    typedQuery.setMaxResults(pageSize);
    List<VCustomer> lists = typedQuery.getResultList();
    try {
      lists.stream().filter(item -> item.getState() != null).forEach(item -> {
        String ref = cachingService.getApParam(ApParamEnum.CUSTOMER_STATE.getValue(), item.getState());
        item.setStateRef(ref);
      });
    } catch (Exception ex) {

    }
    ListResult<VCustomer> result = new ListResult<>();
    result.setData(lists);
    result.setPageSize(pageSize);
    result.setPageIndex(pageIndex);
    result.setTotalCount(totalCount);
    return result;

  }

  @Override
  public Customer get(Long id) {
    return super.get(id);
  }

  @Override
  public Customer save(Customer object) throws Exception {
    return super.save(object);
  }

  @Override
  public Customer update(Customer object, Long id) throws Exception {
    return super.update(object, id);
  }

  @Override
  public Customer updateManage(CustomerLimitDto object, Long id) throws Exception {
    Customer customer = super.get(id);
    customer.setEmail(object.getEmail());
    customer.setIdIssueDate(object.getIdIssueDate());
    customer.setIdIssuePlace(object.getIdIssuePlace());
    return super.update(customer, id);
  }

  @Override
  public Customer delete(Long id) throws Exception {
    return super.delete(id);
  }

  @Override
  public List<Customer> getAll() {
    return super.getAll();
  }

  @Override
  public Customer updateState(Long id, String data) throws Exception {
    Customer customer = super.get(id);
    customer.setState(data);
    super.save(customer);
    return customer;
  }

  @Override
  public Customer updateVerifyState(Long id, String data) throws Exception {
    Customer customer = super.get(id);
    customer.setVerifyState(data);
    super.save(customer);
    return customer;
  }
}
