package com.example.demo.domain.filter;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

public class CustomSpecification<T> implements Specification<T> {
    private final SearchCriteria searchCriteria;
    public CustomSpecification(final SearchCriteria
                                         searchCriteria){
        super();
        this.searchCriteria = searchCriteria;
    }
    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Class<?> type = getType(root, searchCriteria.getFilterKey());
        Object valueToSearch = searchCriteria.getValue();
        Path<?> path = createPath(root, searchCriteria.getFilterKey());
        switch (Objects.requireNonNull(SearchOperation.getSimpleOperation(searchCriteria.getOperation()))) {
            case CONTAINS:
                return cb.like(cb.lower((Expression)path), "%" + valueToSearch + "%");
            case GREATER_THAN_EQUAL:
                if (type.equals(LocalDateTime.class)) {
                    return cb.greaterThanOrEqualTo(path.as(LocalDateTime.class), LocalDateTime.parse(valueToSearch.toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                }
                return cb.greaterThanOrEqualTo(path.as(String.class), valueToSearch.toString());
            case GREATER_THAN:
                if (type.equals(LocalDateTime.class)) {
                    return cb.greaterThan(path.as(LocalDateTime.class), LocalDateTime.parse(valueToSearch.toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                }
                return cb.greaterThan(path.as(String.class), valueToSearch.toString());
            case LESS_THAN:
                if (type.equals(LocalDateTime.class)) {
                    return cb.lessThan(path.as(LocalDateTime.class), LocalDateTime.parse(valueToSearch.toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                }
                return cb.lessThan(path.as(String.class), valueToSearch.toString());
            case LESS_THAN_EQUAL:
                if (type.equals(LocalDateTime.class)) {
                    return cb.lessThanOrEqualTo(path.as(LocalDateTime.class), LocalDateTime.parse(valueToSearch.toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                }
                return cb.lessThanOrEqualTo(path.as(String.class), valueToSearch.toString());
            case EQUAL:
                if (type.equals(LocalDateTime.class)) {
                    return cb.equal(path.as(LocalDateTime.class), LocalDateTime.parse(valueToSearch.toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                } else if (type.equals(UUID.class)) {
                    return cb.equal(path.as(UUID.class), UUID.fromString((String) valueToSearch));
                }
                return cb.equal(path, valueToSearch);
            case NOT_EQUAL:
                if (type.equals(LocalDateTime.class)) {
                    return cb.notEqual(path.as(LocalDateTime.class), LocalDateTime.parse(valueToSearch.toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                }
                return cb.notEqual(path, valueToSearch);
            case NUL:
                return cb.isNull(path);
        }

        return null;
    }

    private Path<?> createPath(Root<T> root, String filterKey) {
        String[] parentChildRelation = filterKey.split("\\.");
        Path<?> path = root;
        for (String filter: parentChildRelation) {
            path = path.get(filter);
        }
        return path;
    }

    private Class<?> getType(Root<T> root, String filterKey) {
        String[] parentChildRelation = filterKey.split("\\.");
        Path<?> path = root;
        for (String filter: parentChildRelation) {
            path = path.get(filter);
        }
        return path.getJavaType();
    }
}
