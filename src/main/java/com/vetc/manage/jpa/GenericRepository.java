package com.vetc.manage.jpa;

import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.request.SearchBase;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.persistence.EntityTransaction;
import javax.persistence.metamodel.SingularAttribute;
import javax.xml.bind.ValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @Author HungVM
 */

@NoRepositoryBean
public interface GenericRepository<T, ID extends Serializable> extends JpaRepository<T, ID>,
    PagingAndSortingRepository<T, ID>, JpaSpecificationExecutor<T> {

  EntityTransaction getTransaction();

  SingularAttribute getIdAttribute();

  List<T> findByAttributeContainsText(String attributeName, String text);

  List<T> findByAttributeName(String attributeName, Object value);

  List<T> findByAttributeName(String attributeName, Object[] values);

  List<T> findByIds(List<ID> ids);

  Long countById(ID id);

  Long countByCondition(String attributeName, Object value);

  Long countByCondition(Condition cond);

  Long countByCondition(Map<String, Object> mapPropertyWhere);

  Long countByCondition(List<CondItem> condItems);

  Page<T> findByCondition(Condition cond);

  List<T> findByCondition(List<CondItem> condItems);

  void updateByProperty(ID id, String propertyName, Object value);

  void updateByProperty(ID id, Map<String, Object> mapPropertyUpdate);

  void updateByProperty(Map<String, Object> mapPropertyUpdate,
      Map<String, Object> mapPropertyWhere);

  void updateByProperty(List<CondItem> lstUpdate, List<CondItem> lstWhere);

  Optional<T> findSingleByProperty(Map<String, Object> mapPropertyWhere);

  Optional<T> findSingleByProperty(List<CondItem> condItems);

  Optional<T> findSingleByProperty(String propertyName, Object value);

  void deleteByCondition(List<CondItem> condItems);

  public ListResult search(SearchBase searchBase) throws ValidationException;

  List<T> searchByList(String field, List<String> items);

  List<T> searchAll(Map<String, String> requestFilter);

  <S extends T> List<S> saveAll(Iterable<S> entities);
}
