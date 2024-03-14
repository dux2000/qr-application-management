package com.example.demo.domain.filter;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class CustomSpecification<T> implements Specification<T> {
    private final SearchCriteria searchCriteria;
    public CustomSpecification(final SearchCriteria
                                         searchCriteria){
        super();
        this.searchCriteria = searchCriteria;
    }
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Class<?> type = root.get(searchCriteria.getFilterKey()).getJavaType();
        Object valueToSearch = searchCriteria.getValue();
        switch (Objects.requireNonNull(SearchOperation.getSimpleOperation(searchCriteria.getOperation()))) {
            case CONTAINS:
                return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + valueToSearch + "%");
            case GREATER_THAN_EQUAL:
                if (type.equals(LocalDateTime.class)) {
                    return cb.greaterThanOrEqualTo(root.get(searchCriteria.getFilterKey()).as(LocalDateTime.class), LocalDateTime.parse(valueToSearch.toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                }
                return cb.equal(root.get(searchCriteria.getFilterKey()), valueToSearch);
            case EQUAL:
                if (type.equals(LocalDateTime.class)) {
                    return cb.equal(root.get(searchCriteria.getFilterKey()).as(LocalDateTime.class), LocalDateTime.parse(valueToSearch.toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                }
                return cb.equal(root.get(searchCriteria.getFilterKey()), valueToSearch);
            case NOT_EQUAL:
                if (type.equals(LocalDateTime.class)) {
                    return cb.notEqual(root.get(searchCriteria.getFilterKey()).as(LocalDateTime.class), LocalDateTime.parse(valueToSearch.toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                }
                return cb.equal(root.get(searchCriteria.getFilterKey()), valueToSearch);
        }

        return null;
    }
}
