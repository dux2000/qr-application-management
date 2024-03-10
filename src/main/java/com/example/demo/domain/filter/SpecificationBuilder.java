package com.example.demo.domain.filter;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SpecificationBuilder<T> {
    private final List<SearchCriteria> params;

    public SpecificationBuilder(){
        this.params = new ArrayList<>();
    }

    public final SpecificationBuilder<T> with(String key,
                                              String operation, Object value){
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public final SpecificationBuilder<T> with(SearchCriteria
                                                      searchCriteria){
        params.add(searchCriteria);
        return this;
    }

    public Specification<T> build(){
        if(params.isEmpty()){
            return null;
        }

        Specification<T> result = new CustomSpecification<>(this.params.get(0));
        for (int idx = 1; idx < params.size(); idx++){
            SearchCriteria criteria = params.get(idx);
            result =  SearchOperation.getDataOption(criteria
                    .getDataOption()) == SearchOperation.ALL
                    ? Specification.where(result).and(new
                    CustomSpecification<>(criteria))
                    : Specification.where(result).or(
                    new CustomSpecification<>(criteria));
        }
        return result;
    }
}
