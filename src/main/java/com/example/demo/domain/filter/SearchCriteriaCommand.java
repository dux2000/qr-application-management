package com.example.demo.domain.filter;

import lombok.Data;

import java.util.List;

@Data
public class SearchCriteriaCommand {
    private List<SearchCriteria> searchCriteriaList;
    private String dataOption;
}
