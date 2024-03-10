package com.example.demo.domain.filter;

import jakarta.persistence.criteria.*;
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
        String strToSearch = searchCriteria.getValue()
                .toString().toLowerCase();

        switch (Objects.requireNonNull(SearchOperation.getSimpleOperation(searchCriteria.getOperation()))) {
            case CONTAINS:
                return cb.like(cb.lower(root.get(searchCriteria.getFilterKey())), "%" + strToSearch + "%");
            case GREATER_THAN_EQUAL:
                Class<?> type = root.get(searchCriteria.getFilterKey()).getJavaType();
                if (type.equals(LocalDateTime.class)) {
                    LocalDateTime localDateTime = LocalDateTime.parse(strToSearch, DateTimeFormatter.ISO_LOCAL_DATE_TIME);

                    return cb.greaterThanOrEqualTo(root.get(searchCriteria.getFilterKey()).as(LocalDateTime.class), localDateTime);
                }
                return cb.greaterThanOrEqualTo(root.get(searchCriteria.getFilterKey()), strToSearch);
        }
        return null;
    }
}
