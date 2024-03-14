package com.example.demo.domain.filter;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SpecificationBuilder<T> {
    private final List<SearchCriteria> params;

    public SpecificationBuilder(){
        this.params = new ArrayList<>();
    }

//    public final SpecificationBuilder<T> with(String key,
//                                              String operation, Object value){
//        params.add(new SearchCriteria(key, operation, value));
//        return this;
//    }

    public final SpecificationBuilder<T> with(SearchCriteria
                                                      searchCriteria){
        params.add(searchCriteria);
        return this;
    }

    public Specification<T> build(SearchFilter searchRequest){
        if (searchRequest == null) return null;

        Specification<T> result = new CustomSpecification<>(searchRequest.getSearchCriteria().get(0));
        for (int idx = 1; idx < searchRequest.getSearchCriteria().size(); idx++) {
            if ("AND".equalsIgnoreCase(searchRequest.getLogicalOperator()))
                result = Specification.where(result).and(new CustomSpecification<>(searchRequest.getSearchCriteria().get(idx)));
            else
                result = Specification.where(result).or(new CustomSpecification<>(searchRequest.getSearchCriteria().get(idx)));
        }

        if (searchRequest.getSubFilters() != null && !searchRequest.getSubFilters().isEmpty()) {
            for (int j = 0; j < searchRequest.getSubFilters().size(); j++) {
                Specification<T> subFilterSpec = build(searchRequest.getSubFilters().get(j));

                if ("AND".equalsIgnoreCase(searchRequest.getLogicalOperator())) {
                    result = Specification.where(result).and(subFilterSpec);
                } else {
                    result = Specification.where(result).or(subFilterSpec);
                }
            }
        }

        return result;
    }
}
