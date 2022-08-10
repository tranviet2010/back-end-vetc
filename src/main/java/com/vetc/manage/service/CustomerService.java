package com.vetc.manage.service;

import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.dto.request.CustomerLimitDto;
import com.vetc.manage.entity.Customer;
import com.vetc.manage.entity.view.VCustomer;
import com.vetc.manage.entity.view.VCustomerDetail;
import java.util.List;
import java.util.Map;

public interface CustomerService {

  VCustomerDetail getDetail(Long id) throws Exception;

  ListResult<Customer> search(SearchBase searchBase) throws Exception;

//  ListResult<VCustomer> searchView(SearchBase searchBase) throws Exception;


  ListResult<VCustomer> searchview(SearchBase searchBase) throws Exception;

  ListResult<VCustomer> searchViewTime(Map<String, String> request, int pageSize, int pageIndex) throws Exception;

  Customer get(Long id);

  Customer save(Customer object) throws Exception;

  Customer update(Customer object, Long id) throws Exception;

  Customer updateManage(CustomerLimitDto object, Long id) throws Exception;

  Customer delete(Long id) throws Exception;

  List<Customer> getAll();

  Customer updateState(Long id, String data) throws Exception;

  Customer updateVerifyState(Long id, String data) throws Exception;
}
