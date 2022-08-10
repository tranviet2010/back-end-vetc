package com.vetc.manage.common;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class SetPredicate {

    public List<Predicate> setPredicates(CriteriaBuilder cb, Root<?> root, Map<String, String> request) {
        List<Predicate> predicates = new ArrayList<>();
        for (Map.Entry<String, String> data : request.entrySet()) {
            if (data.getKey().equals("limit")
                    || data.getKey().equals("page")) {
                continue;
            }
            if (data.getValue() != null) {
                Predicate predicate;
                if (data.getValue().contains(":")) {
                    try {
                        GetvalueToObject getvalueToObject = getValue(data.getValue());
                        String operator = getvalueToObject.getValue1();
                        if (operator.equals("like")) {
                            String value = "%" + getvalueToObject.getValue2() + "%";
                            predicate = cb.like(root.get(data.getKey()), value);
                            predicates.add(predicate);
                        } else if (operator.equals("startwith")) {
                            String value = getvalueToObject.getValue2() + "%";
                            predicate = cb.like(root.get(data.getKey()), value);
                            predicates.add(predicate);
                        } else if (operator.equals("endwith")) {
                            String value = "%" + getvalueToObject.getValue2();
                            predicate = cb.like(root.get(data.getKey()), value);
                            predicates.add(predicate);
                        } else if (operator.equals("betweendate")) {
                            GetvalueToObject betweenValue = getTimeValue(getvalueToObject.getValue2());
                            predicate = cb.between(root.get(
                                            data.getKey()),
                                    convertTimestamp(betweenValue.getValue1()),
                                    convertTimestamp(betweenValue.getValue2()));
                            predicates.add(predicate);
                        }
                    } catch (Exception e) {
                        log.error("Data invalid!", e);
                        return null;
                    }
                } else {
                    try {
                        predicate = cb.equal(root.get(data.getKey()), data.getValue());
                        predicates.add(predicate);
                    } catch (Exception e) {
                        log.error("Data invalid!", e);
                        return null;
                    }
                }
            }
        }
        return predicates;
    }

    public Date convertTimestamp(String time) {
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        try {
            date = formatter.parse(time);
        } catch (ParseException e) {
            log.error("Convert TimeStamp Error", e);
        }
        return date;
    }


    public GetvalueToObject getTimeValue(String timeData) {
        GetvalueToObject getvalueToObject = new GetvalueToObject();
        String[] result = timeData.split(",");
        getvalueToObject.setValue1(result[0]);
        getvalueToObject.setValue2(result[1]);
        return getvalueToObject;
    }

    public GetvalueToObject getValue(String request) {
        GetvalueToObject getvalueToObject = new GetvalueToObject();
        String[] result = request.split(":");
        getvalueToObject.setValue1(result[0]);
        getvalueToObject.setValue2(result[1]);
        return getvalueToObject;
    }

    @Data
    private static class GetvalueToObject {
        String value1;
        String value2;
    }
}
