package com.vetc.manage.service.common;

import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.enums.ExceptionEnum;
import com.vetc.manage.exception.ErrorListException;
import com.vetc.manage.exception.ValidationException;
import com.vetc.manage.jpa.GenericRepository;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j

public class ServiceBase<T, D extends Serializable, R extends GenericRepository<T, D>> {

  @Autowired
  protected R repository;

  @Autowired
  protected EntityManager entityManager;

  public List<T> searchAll(Map<String, String> requestFilter) {
    return repository.searchAll(requestFilter);
  }

  public ListResult<T> search(SearchBase searchBase) throws Exception {
    ListResult<T> existed = repository.search(searchBase);
//    if (existed == null) {
//      throw new ValidationException(ExceptionEnum.RECORD_NOT_FOUND.getCode(),
//          ExceptionEnum.RECORD_NOT_FOUND.getMessage());
//    }
    return existed;
  }

  public T get(D id) {
    T existed = repository.findById(id).orElse(null);
    if (existed == null) {
      throw new ValidationException(ExceptionEnum.RECORD_NOT_FOUND.getCode(),
          "Không tìm thấy bản ghi này!");
    }
    return existed;
  }

  public T save(T object) throws Exception {
    if (object == null) {
      throw new ValidationException(ExceptionEnum.DATA_INVALID.getCode(),
          ExceptionEnum.DATA_INVALID.getMessage());
    }
    try {
      T result = repository.save(object);
      return result;
    }catch (Exception ex){
      log.error(ex.getMessage());
      throw new ErrorListException(ex.getMessage());
    }
  }

  public T update(T object, D id) throws Exception {
    T existed = repository.findById(id).orElse(null);
    if (existed == null) {
      throw new ValidationException(ExceptionEnum.RECORD_NOT_FOUND.getCode(),
          ExceptionEnum.RECORD_NOT_FOUND.getMessage());
    }
//		BEUtils.copyMatchingFields(object, existed);
    T result = repository.save(object);
    return result;
  }

  public T delete(D id) throws Exception {
    T existed = repository.findById(id).orElse(null);
    if (existed == null) {
      throw new ValidationException(ExceptionEnum.RECORD_NOT_FOUND.getCode(),
          ExceptionEnum.RECORD_NOT_FOUND.getMessage());
    }
    // xoa bang
    repository.delete(existed);
    return existed;
  }

  public List<T> searchByList(String field, List<String> items) {
    return repository.searchByList(field, items);
  }

  public List<T> saveAll(List<T> list) {
    if (list.isEmpty()) {
      throw new ValidationException(ExceptionEnum.DATA_INVALID.getCode(),
          ExceptionEnum.DATA_INVALID.getMessage());
    }
    return repository.saveAll(list);
  }

  public List<T> getAll() {
    return repository.findAll();
  }
}
