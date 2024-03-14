package com.example.demo.domain.filter;

import lombok.Data;

import java.util.List;

@Data
public class SearchFilter {
    private List<SearchCriteria> searchCriteria;
    private String logicalOperator;
    private List<SearchFilter> subFilters;
}
