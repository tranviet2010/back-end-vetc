package com.vetc.manage.jpa;

import java.util.HashMap;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public abstract class GenericRepositoryCustom {

    @PersistenceContext
    protected EntityManager entityManager;

    public HashMap<String, Object> newMapParameters() {
        return new HashMap<>();
    }

    public String createParameter(String condition, String sql, HashMap<String, Object> mapParams, String key,
                                  Object value) {
        if (value != null) {
            sql += condition;
            mapParams.put(key, value);
        }
        return sql;
    }

    public Query pagingQuery(Query query, Integer pageNumber, Integer maxItem) {
        if (pageNumber != null && maxItem != null && maxItem > 0 && pageNumber >= 0) {
            query.setMaxResults(maxItem);
            query.setFirstResult(pageNumber);
        }
        return query;
    }

    public Query setParametersByMap(Query query, HashMap<String, Object> mapParams) {
        mapParams.keySet().forEach(param -> {
            Object value = mapParams.get(param);
            query.setParameter(param, value);
        });
        return query;
    }

    public int getTotalPage(int totalRecord, Integer pageSize) {
        int result = 1;
        if (pageSize != null && pageSize > 0) {
            result = (totalRecord % pageSize) == 0 ? totalRecord / pageSize : totalRecord / pageSize + 1;
        }
        return result;
    }
}

