package com.vetc.manage.service.impl;

import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.entity.Product;
import com.vetc.manage.repository.ProductRepository;
import com.vetc.manage.service.ProductService;
import com.vetc.manage.service.common.ServiceBase;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl
    extends ServiceBase<Product, Long, ProductRepository>
    implements ProductService {

  @Override
  public List<Product> searchAll(Map<String, String> requestFilter) {
    return super.searchAll(requestFilter);
  }

  @Override
  public ListResult<Product> search(SearchBase searchBase) throws Exception {
    return super.search(searchBase);
  }

  @Override
  public Product get(Long id) {
    return super.get(id);
  }

  @Override
  public Product save(Product object) throws Exception {
    return super.save(object);
  }

  @Override
  public Product update(Product object, Long id) throws Exception {
    return super.update(object, id);
  }

  @Override
  public Product delete(Long id) throws Exception {
    return super.delete(id);
  }

  @Override
  public List<Product> searchByList(String field, List<String> items) {
    return super.searchByList(field, items);
  }

  @Override
  public List<Product> saveAll(List<Product> list) {
    return super.saveAll(list);
  }

  @Override
  public List<Product> getAll() {
    return super.getAll();
  }
}
