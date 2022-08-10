package com.vetc.manage.service;

import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.entity.AccountLimit;
import com.vetc.manage.entity.Product;
import java.util.List;
import java.util.Map;

public interface ProductService {

  List<Product> searchAll(Map<String, String> requestFilter);

  ListResult<Product> search(SearchBase searchBase) throws Exception;

  Product get(Long id);

  Product save(Product object) throws Exception;

  Product update(Product object, Long id) throws Exception;

  Product delete(Long id) throws Exception;

  List<Product> searchByList(String field, List<String> items);

  List<Product> saveAll(List<Product> list);

  List<Product> getAll();
}
