package com.vetc.manage.jpa;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vetc.manage.controller.dto.ListResult;
import com.vetc.manage.controller.request.SearchBase;
import com.vetc.manage.controller.request.SearchBo;
import com.vetc.manage.enums.ExceptionEnum;
import com.vetc.manage.exception.ValidationException;
import com.vetc.manage.utils.DateUtil;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.IdentifiableType;
import javax.persistence.metamodel.SingularAttribute;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author HungVM
 */
@NoRepositoryBean
@Slf4j
public class GenericRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
    implements GenericRepository<T, ID> {

  private EntityManager entityManager;

  public Class<T> getEntityClass() {
    return super.getDomainClass();
  }

  // serach all by list
  //Hanq
  public List<T> searchByList(String field, List<String> items) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<T> query = cb.createQuery(getEntityClass());
    Root<T> root = query.from(getEntityClass());
    List<Predicate> predicates = new ArrayList<>();
    items.stream().filter(item -> item != null).forEach(item -> {
      Predicate predicate = cb.equal(root.get(field), item);
      predicates.add(predicate);
    });
    query.select(root).where(predicates.toArray(new Predicate[]{}));

    TypedQuery<T> typedQuery = entityManager.createQuery(query);
    List<T> lists = typedQuery.getResultList();
    return lists;
  }

  public GenericRepositoryImpl(Class<T> domainClass, EntityManager em) {
    this(JpaEntityInformationSupport.getEntityInformation(domainClass, em), em);
  }

  public GenericRepositoryImpl(JpaEntityInformation<T, ?> entityInformation,
      EntityManager entityManager) {
    super(entityInformation, entityManager);
    this.entityManager = entityManager;
  }

  public EntityTransaction getTransaction() {
    return entityManager.getTransaction();
  }

  public SingularAttribute getIdAttribute() {
    IdentifiableType<T> of = (IdentifiableType<T>) entityManager.getMetamodel()
        .managedType(getDomainClass());
    return of.getId(of.getIdType().getJavaType());
  }

  @Override
  @Transactional(readOnly = true)
  public List<T> findByAttributeContainsText(String attributeName, String text) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<T> cQuery = builder.createQuery(getDomainClass());
    Root<T> root = cQuery.from(getDomainClass());
    cQuery.select(root).where(builder.like(root.get(attributeName), "%" + text + "%"));
    TypedQuery<T> query = entityManager.createQuery(cQuery);
    return query.getResultList();
  }

  @Override
  @Transactional(readOnly = true)
  public List<T> findByAttributeName(String attributeName, Object[] values) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<T> cQuery = cb.createQuery(getDomainClass());
    Root<T> root = cQuery.from(getDomainClass());
    cQuery.select(root);
    Expression<String> parentExpression = root.get(attributeName);
    Predicate parentPredicate = parentExpression.in(Arrays.asList(values));
    cQuery.where(parentPredicate);
    cQuery.orderBy(cb.asc(root.get(attributeName)));
    TypedQuery<T> query = entityManager.createQuery(cQuery);
    return query.getResultList();
  }

  @Override
  @Transactional(readOnly = true)
  public List<T> findByAttributeName(String attributeName, Object value) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<T> cQuery = cb.createQuery(getDomainClass());
    Root<T> root = cQuery.from(getDomainClass());
    cQuery.select(root);
    cQuery.where(cb.equal(root.get(attributeName), value));
    TypedQuery<T> query = entityManager.createQuery(cQuery);
    return query.getResultList();
  }

  @Override
  @Transactional(readOnly = true)
  public List<T> findByIds(List<ID> ids) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<T> cQuery = cb.createQuery(getDomainClass());
    Root<T> root = cQuery.from(getDomainClass());
    cQuery.select(root);
    Expression<String> parentExpression = root.get(getIdAttribute().getName());
    Predicate parentPredicate = parentExpression.in(ids);
    cQuery.where(parentPredicate);
    cQuery.orderBy(cb.asc(root.get(getIdAttribute().getName())));
    TypedQuery<T> query = entityManager.createQuery(cQuery);
    List<T> lsResult = query.getResultList();
    return lsResult;
  }

  @Override
  @Transactional(readOnly = true)
  public Long countById(ID id) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<T> cq = cb.createQuery(getDomainClass());
    // Create Count Query
    CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
    Root<T> root = countQuery.from(getDomainClass());

    List<Predicate> predicates = new ArrayList<>();
    predicates.add(buildPredicateOperator(cb, root, getIdAttribute().getName(), id, null));
    countQuery.select(cb.count(root)).where(cb.and(predicates.toArray(new Predicate[0])));

    // Fetches the count of all Books as per given criteria
    return entityManager.createQuery(countQuery).getSingleResult();
  }

  @Override
  @Transactional(readOnly = true)
  public Long countByCondition(String attributeName, Object value) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<T> cq = cb.createQuery(getDomainClass());
    // Create Count Query
    CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
    Root<T> root = countQuery.from(getDomainClass());

    List<Predicate> predicates = new ArrayList<>();
    predicates.add(buildPredicateOperator(cb, root, attributeName, value, null));
    countQuery.select(cb.count(root)).where(cb.and(predicates.toArray(new Predicate[0])));

    // Fetches the count of all Books as per given criteria
    return entityManager.createQuery(countQuery).getSingleResult();
  }

  @Override
  @Transactional(readOnly = true)
  public Long countByCondition(Condition cond) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);

    List<Predicate> predicates = new ArrayList<>();
    Root<T> root = countQuery.from(getDomainClass());

    cond.getParams().keySet().forEach(attributeName -> {
      Object value = cond.getParams().get(attributeName);
      String operator = cond.getOperator(attributeName);
      predicates.add(buildPredicateOperator(cb, root, attributeName, value, operator));
    });

    // Create Count Query
    countQuery.select(cb.count(root)).where(cb.and(predicates.toArray(new Predicate[0])));
    // Fetches the count of all Books as per given criteria
    return entityManager.createQuery(countQuery).getSingleResult();
  }

  @Override
  @Transactional(readOnly = true)
  public Long countByCondition(Map<String, Object> mapPropertyWhere) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<T> cq = cb.createQuery(getDomainClass());

    Root<T> root = cq.from(getDomainClass());
    List<Predicate> predicates = new ArrayList<>();

    mapPropertyWhere.keySet().forEach(attributeName -> {
      Object value = mapPropertyWhere.get(attributeName);
      predicates.add(buildPredicateOperator(cb, root, attributeName, value, null));
    });

    cq.where(predicates.stream().toArray(Predicate[]::new));

    // Create Count Query
    CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
    Root<T> booksRootCount = countQuery.from(getDomainClass());
    countQuery.select(cb.count(booksRootCount)).where(cb.and(predicates.toArray(new Predicate[0])));

    // Fetches the count of all Books as per given criteria
    return entityManager.createQuery(countQuery).getSingleResult();
  }

  @Override
  @Transactional(readOnly = true)
  public Long countByCondition(List<CondItem> condItems) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<T> cq = cb.createQuery(getDomainClass());

    Root<T> root = cq.from(getDomainClass());
    List<Predicate> predicates = new ArrayList<>();

    condItems.forEach(item -> {
      predicates.add(
          buildPredicateOperator(cb, root, item.getAttributeName(), item.getValue(),
              item.getOperator()));
    });

    cq.where(predicates.stream().toArray(Predicate[]::new));

    // Create Count Query
    CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
    Root<T> booksRootCount = countQuery.from(getDomainClass());
    countQuery.select(cb.count(booksRootCount)).where(cb.and(predicates.toArray(new Predicate[0])));

    // Fetches the count of all Books as per given criteria
    return entityManager.createQuery(countQuery).getSingleResult();
  }

  @Override
  @Transactional(readOnly = true)
  public Page<T> findByCondition(Condition cond) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<T> cq = cb.createQuery(getDomainClass());

    Root<T> root = cq.from(getDomainClass());
    List<Predicate> predicates = new ArrayList<>();

    cond.getLsCondItem().forEach(item -> {
      predicates.add(buildPredicateOperator(cb, root, item));
    });

    cq.where(predicates.stream().toArray(Predicate[]::new));
    buildOrder(cb, cq, root, cond);

    Pageable pageable = buildPageable(cond);
    if (pageable != null) {
      int pageNo = (int) pageable.getOffset();
      int pageSize = (int) pageable.getPageSize();

      List<T> result = entityManager.createQuery(cq).setFirstResult((int) pageable.getOffset())
          .setMaxResults(pageable.getPageSize()).getResultList();
      // Create Count Query
      CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
      Root<T> booksRootCount = countQuery.from(getDomainClass());
      countQuery.select(cb.count(booksRootCount))
          .where(cb.and(predicates.toArray(new Predicate[0])));

      // Fetches the count of all Books as per given criteria
      Long count = entityManager.createQuery(countQuery).getSingleResult();

      return new PageImpl<>(result, pageable, count);
    } else {
      TypedQuery<T> query = entityManager.createQuery(cq);
      return new PageImpl<>(query.getResultList());
    }
  }

  @Override
  public List<T> findByCondition(List<CondItem> condItems) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<T> cq = cb.createQuery(getDomainClass());

    Root<T> root = cq.from(getDomainClass());
    List<Predicate> predicates = new ArrayList<>();

    condItems.forEach(item -> {
      predicates.add(buildPredicateOperator(cb, root, item));
    });

    cq.where(predicates.stream().toArray(Predicate[]::new));
    TypedQuery<T> query = entityManager.createQuery(cq);
    return query.getResultList();
  }

  @Override
  public void updateByProperty(ID id, String propertyName, Object value) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaUpdate<T> cu = cb.createCriteriaUpdate(getDomainClass());
    Root e = cu.from(getDomainClass());
    cu.set(propertyName, value);
    cu.where(cb.equal(e.get(getIdAttribute().getName()), id));
    // perform update
    entityManager.createQuery(cu).executeUpdate();
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<T> findSingleByProperty(Map<String, Object> mapPropertyWhere) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<T> cq = cb.createQuery(getDomainClass());

    Root<T> root = cq.from(getDomainClass());
    List<Predicate> predicates = new ArrayList<>();

    mapPropertyWhere.keySet().forEach(attributeName -> {
      Object value = mapPropertyWhere.get(attributeName);
      predicates.add(buildPredicateOperator(cb, root, attributeName, value, null));
    });

    cq.where(predicates.stream().toArray(Predicate[]::new));

    TypedQuery<T> query = entityManager.createQuery(cq);

    return query.getResultList().stream().findFirst();
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<T> findSingleByProperty(String propertyName, Object value) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<T> cq = cb.createQuery(getDomainClass());

    Root<T> root = cq.from(getDomainClass());
    List<Predicate> predicates = new ArrayList<>();
    predicates.add(buildPredicateOperator(cb, root, propertyName, value, null));
    cq.where(predicates.stream().toArray(Predicate[]::new));

    TypedQuery<T> query = entityManager.createQuery(cq);

    return query.getResultList().stream().findFirst();
  }

  @Override
  @Transactional(readOnly = true)
  public Optional<T> findSingleByProperty(List<CondItem> lstItems) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<T> cq = cb.createQuery(getDomainClass());

    Root<T> root = cq.from(getDomainClass());
    List<Predicate> predicates = new ArrayList<>();

    lstItems.forEach(item -> {
      predicates.add(
          buildPredicateOperator(cb, root, item.getAttributeName(), item.getValue(),
              item.getOperator()));
    });

    cq.where(predicates.stream().toArray(Predicate[]::new));

    TypedQuery<T> query = entityManager.createQuery(cq);

    return query.getResultList().stream().findFirst();
  }

  @Override
  public void deleteByCondition(List<CondItem> condItems) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaDelete<T> delete = cb.createCriteriaDelete(getDomainClass());
    Root<T> root = delete.from(getDomainClass());
    List<Predicate> predicates = new ArrayList<>();
    for (CondItem item : condItems) {
      predicates.add(
          buildPredicateOperator(cb, root, item.getAttributeName(), item.getValue(),
              item.getOperator()));
    }
    delete.where(predicates.stream().toArray(Predicate[]::new));
    entityManager.createQuery(delete).executeUpdate();
  }

  @Override
  public void updateByProperty(ID id, Map<String, Object> mapPropertyUpdate) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaUpdate<T> cu = cb.createCriteriaUpdate(getDomainClass());
    Root e = cu.from(getDomainClass());
    for (String propertyName : mapPropertyUpdate.keySet()) {
      cu.set(propertyName, mapPropertyUpdate.get(propertyName));
    }
    cu.where(cb.equal(e.get(getIdAttribute().getName()), id));
    // perform update
    entityManager.createQuery(cu).executeUpdate();
  }

  @Override
  public void updateByProperty(Map<String, Object> mapPropertyUpdate,
      Map<String, Object> mapPropertyWhere) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaUpdate<T> cu = cb.createCriteriaUpdate(getDomainClass());
    Root root = cu.from(getDomainClass());
    for (String propertyName : mapPropertyUpdate.keySet()) {
      cu.set(propertyName, mapPropertyUpdate.get(propertyName));
    }
    List<Predicate> predicates = new ArrayList<>();
    for (String propertyName : mapPropertyWhere.keySet()) {
      predicates.add(
          buildPredicateOperator(cb, root, propertyName, mapPropertyWhere.get(propertyName), null));
    }
    cu.where(predicates.toArray(new Predicate[0]));
    // perform update
    entityManager.createQuery(cu).executeUpdate();
  }

  @Override
  public void updateByProperty(List<CondItem> lstUpdate, List<CondItem> lstWhere) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaUpdate<T> cu = cb.createCriteriaUpdate(getDomainClass());
    Root root = cu.from(getDomainClass());
    for (CondItem item : lstUpdate) {
      cu.set(item.getAttributeName(), item.getValue());
    }
    List<Predicate> predicates = new ArrayList<>();
    for (CondItem item : lstWhere) {
      predicates.add(
          buildPredicateOperator(cb, root, item.getAttributeName(), item.getValue(), null));
    }
    cu.where(predicates.toArray(new Predicate[0]));
    // perform update
    entityManager.createQuery(cu).executeUpdate();
  }

  private void buildOrder(CriteriaBuilder cb, CriteriaQuery<T> cq, Root root, Condition cond) {
    if (cond.getOrderByColumns() != null) {
      String[] arrOrder = cond.getOrderByColumns().split(",");
      if (arrOrder.length > 1) {
        if (arrOrder.length > 0) {
          List<Order> orders = new ArrayList<>(arrOrder.length);
          for (String item : arrOrder) {
            String[] ord = item.split(" ");
            if (ord.length == 2) {
              orders.add("asc".equalsIgnoreCase(ord[1]) ? cb.asc(root.get(ord[0]))
                  : cb.desc(root.get(ord[0])));
            }
          }
          cq.orderBy(orders);
        }
      } else {
        List<Order> orders = new ArrayList<>(arrOrder.length);
        orders.add(cond.isAscOrder() ? cb.asc(root.get(cond.getOrderByColumns()))
            : cb.desc(root.get(cond.getOrderByColumns())));
        cq.orderBy(orders);
      }
    }
  }

  private Pageable buildPageable(Condition cond) {
    if (cond.getFirstItemIndex() != null && cond.getMaxItems() != null
        && cond.getFirstItemIndex() >= 0
        && cond.getMaxItems() > 0) {
      return PageRequest.of(cond.getFirstItemIndex(), cond.getMaxItems());
    }
    return null;
  }

  private Predicate buildPredicateOperator(CriteriaBuilder cb, Root<T> root, CondItem item) {
    Predicate rootPredicate = buildPredicateOperator(cb, root, item.getAttributeName(),
        item.getValue(),
        item.getOperator());
    if (item.getLsAndCondItem() != null && item.getLsAndCondItem().size() > 0) {
      List<Predicate> lsPredicate = new ArrayList<Predicate>();
      for (CondItem row : item.getLsAndCondItem()) {
        lsPredicate.add(
            buildPredicateOperator(cb, root, row.getAttributeName(), row.getValue(),
                row.getOperator()));
      }
      lsPredicate.add(rootPredicate);
      return cb.and(lsPredicate.toArray(new Predicate[lsPredicate.size()]));
    } else if (item.getLsORCondItem() != null && item.getLsORCondItem().size() > 0) {
      List<Predicate> lsPredicate = new ArrayList<Predicate>();
      for (CondItem row : item.getLsORCondItem()) {
        lsPredicate.add(
            buildPredicateOperator(cb, root, row.getAttributeName(), row.getValue(),
                row.getOperator()));
      }
      lsPredicate.add(rootPredicate);
      return cb.or(lsPredicate.toArray(new Predicate[lsPredicate.size()]));
    }
    return rootPredicate;
  }

  private Predicate buildPredicateOperator(CriteriaBuilder cb, Root<T> root, String attributeName,
      Object value,
      String operator) {
    operator = operator != null ? operator : "=";
    Path expression = root.get(attributeName);

    switch (operator) {
      case "=":
        return cb.equal(expression, value);
      case "!=":
        return cb.notEqual(expression, value);
      case "like":
        return cb.like((Expression<String>) expression, "%" + value + "%");
      case "<":
        return cb.lessThan(expression, (Comparable) value);
      case ">":
        return cb.greaterThan(expression, (Comparable) value);
      case "<=":
        return cb.lessThanOrEqualTo(expression, (Comparable) value);
      case ">=":
        return cb.greaterThanOrEqualTo(expression, (Comparable) value);
      case "in":
        Path<Object> path = root.get(attributeName);
        CriteriaBuilder.In<Object> in = cb.in(path);
        if (value instanceof Object[]) {
          for (Object val : (Object[]) value) {
            in.value(val);
          }
        } else {
          ((Collection<?>) value).forEach(item -> in.value(item));
        }
        return in;
      case "between":
        Object[] obj = (Object[]) value;
        return cb.between(expression, (Comparable) obj[0], (Comparable) obj[1]);
      default:
        return cb.equal(expression, value);
    }
  }

  @Override
  @Transactional(readOnly = true)
  public ListResult<T> search(SearchBase searchBase) throws ValidationException {
    Condition cond = new Condition(searchBase.orderByColumns(), searchBase.isAsc(),
        searchBase.getPage(),
        searchBase.getLimit(), true);
    if (searchBase.getLsSearchBo() != null) {
      List<SearchBo> lsSearchs = validParam(searchBase.getLsSearchBo(), getEntityClass());
      for (SearchBo search : lsSearchs) {
        if (search.getOperator() != null && !search.getOperator().isEmpty()) {
          cond.addCondItem(buildCondItem(search));
        }
      }
    }
    Page<?> pageContent = findByCondition(cond);
    return buildListResponse(pageContent);
  }

  @Override
  public List<T> searchAll(Map<String, String> requestFilter) {
    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    CriteriaQuery<T> query = cb.createQuery(getEntityClass());
    Root<T> root = query.from(getDomainClass());

    List<Predicate> predicates = buildPredicate(cb, root, requestFilter);
//		if (predicates == null) {
//			throw new ParamsException(EnumCodeResponse.FILED_DOES_NOT_EXIST);
//		}
    query.select(root).where(predicates.toArray(new Predicate[]{}));
    TypedQuery<T> typedQuery = entityManager.createQuery(query);
//		int totalCount = typedQuery.getResultList().size();
//		typedQuery.setFirstResult((pageIndex - 1) * pageSize);
//		typedQuery.setMaxResults(pageSize);
    List<T> lsResult = typedQuery.getResultList();
    if (lsResult.isEmpty()) {
      throw new ValidationException(ExceptionEnum.RECORD_NOT_FOUND.getCode(),
          ExceptionEnum.RECORD_NOT_FOUND.getMessage());
    }
    return lsResult;
  }

  public List<Predicate> buildPredicate(CriteriaBuilder cb, Root<?> root,
      Map<String, String> request) {
    List<Predicate> predicates = new ArrayList<>();
    for (Map.Entry<String, String> data : request.entrySet()) {
      if (data.getValue() != null) {
        Predicate predicate;
        try {
          predicate = cb.equal(root.get(data.getKey()), data.getValue());
          predicates.add(predicate);
        } catch (Exception ex) {
          log.warn("Filed <" + data.getKey() + "> not found!");
          log.warn(ex.getMessage());
        }
      }
    }
    return predicates;
  }

  private CondItem buildCondItem(SearchBo searchBo) {
    CondItem item = new CondItem(searchBo.getName(), searchBo.getOperator(), searchBo.getValue());
    if (searchBo.getAndSearch() != null && searchBo.getAndSearch().size() >= 0) {
      searchBo.getAndSearch()
          .forEach(
              t -> item.addAndCondItem(new CondItem(t.getName(), t.getOperator(), t.getValue())));
    }
    if (searchBo.getOrSearch() != null && searchBo.getOrSearch().size() >= 0) {
      searchBo.getOrSearch()
          .forEach(
              t -> item.addOrCondItem(new CondItem(t.getName(), t.getOperator(), t.getValue())));
    }
    return item;
  }

  private ListResult buildListResponse(Page<?> pageContent) {
    ListResult listResult = new ListResult();
    listResult.setData(pageContent.getContent());
    listResult.setObject("list");
    listResult.setTotalCount((int) pageContent.getTotalElements());
    listResult.setPageSize(pageContent.getSize());
    listResult.setPageIndex(pageContent.getNumber() + 1);
    return listResult;
  }

  private Map<String, Class<?>> convertToString(Class<?> classt) {
    Field[] allFields = classt.getDeclaredFields();
    if (allFields == null) {
      return null;
    }
    Map<String, Class<?>> lsField = new Hashtable<String, Class<?>>();
    for (Field field : allFields) {
      String annotationName = getAnnotationValue(field);
      String fieldName = field.getName();
      if (!StringUtils.isEmpty(annotationName)) {
        lsField.put(annotationName.toLowerCase(), field.getType());
      }
      if (!StringUtils.isEmpty(fieldName)) {
        lsField.put(fieldName.toLowerCase(), field.getType());
      }
    }
    return lsField;
  }

  private String getAnnotationValue(Field field) {
    try {
      return field.getAnnotation(JsonProperty.class).value();
    } catch (Exception e) {
      // TODO: handle exception
    }
    return null;
  }

  private List<SearchBo> validParam(List<SearchBo> lsSearch, Class<?> classt)
      throws ValidationException {
    Map<String, Class<?>> lsField = convertToString(classt);
    List<SearchBo> lsSearchs = new ArrayList<SearchBo>();
    if (lsField == null) {
      return lsSearchs;
    }
    for (SearchBo search : lsSearch) {
      Class<?> fieldClass = lsField.get(search.getName().toLowerCase());
      if (fieldClass != null) {
        if (!validateClassType(fieldClass, search)) {
          throw new ValidationException(403, "field " + search.getName() + " with value "
              + String.valueOf(search.getValue()) + " not match with type " + fieldClass.getName());
        }
        if (search.getAndSearch() != null && search.getAndSearch().size() >= 0) {
          search.getAndSearch().forEach(t -> {
            if (!validateClassType(fieldClass, t)) {
              throw new ValidationException(403,
                  "field " + search.getName() + " with value " + String.valueOf(t.getValue())
                      + " not match with type " + fieldClass.getName());
            }
          });
        }
        if (search.getOrSearch() != null && search.getOrSearch().size() >= 0) {
          search.getOrSearch().forEach(t -> {
            if (!validateClassType(fieldClass, t)) {
              throw new ValidationException(403,
                  "field " + search.getName() + " with value " + String.valueOf(t.getValue())
                      + " not match with type " + fieldClass.getName());
            }
          });
        }

        search.setName(search.getName());
        lsSearchs.add(search);
      }
    }
    return lsSearchs;
  }

  private boolean validateClassType(Class<?> classs, SearchBo search) {
    try {
      if (search == null || search.getValue() == null) {
        return true;
      }
      if (classs.getName().contains("Double") || classs.getName().contains("Long")
          || classs.getName().contains("Integer") || classs.getName() == "long"
          || classs.getName() == "int"
          || classs.getName() == "double") {
        return NumberUtils.isCreatable(String.valueOf(search.getValue()));
      } else if (classs.getName().contains("Date") && search.getValue().getClass().getName()
          .contains("String")) {
        search.setValue(formatValue(classs.getName(), String.valueOf(search.getValue())));
        return true;
      }
      return true;
    } catch (Exception e) {
      // TODO: handle exception
    }
    return false;
  }

  private Object formatValue(String name, String value) {
    Object object = null;
    if (name.toLowerCase().contains("datetime")) {
      object = DateUtil.convertDateTime(value);
      if (object == null) {
        throw new ValidationException(400,
            "parameter " + name + " not correct format datetime with value " + value);
      }
      return object;
    }
    object = DateUtil.convertDate(value);
    if (object == null) {
      throw new ValidationException(400,
          "parameter " + name + " not correct format date with value " + value);
    }
    return object;

  }

  public Query setParametersByMap(Query query, HashMap<String, Object> mapParams) {
    for (String param : mapParams.keySet()) {
      Object value = mapParams.get(param);
      if (value instanceof Collection<?>) {
        query.setParameter(param, value);
      } else if (value instanceof String[]) {
        query.setParameter(param, value);
      } else {
        query.setParameter(param, value);
      }
    }
    return query;
  }

  public static <T> T map(Class<T> type, Object[] tuple) {
    List<Class<?>> tupleTypes = new ArrayList<>();
    for (Object field : tuple) {
      tupleTypes.add(field.getClass());
    }
    try {
      Constructor<T> ctor = type.getConstructor(tupleTypes.toArray(new Class<?>[tuple.length]));
      return ctor.newInstance(tuple);

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
