package com.example.demo.domain.filter;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.function.Function;

@Data
@AllArgsConstructor
public class SearchResponse<T> {
    private List<T> data;
    private Long total;

    public <R> SearchResponse<R> convert(Function<T, R> mapper) {
        return new SearchResponse<>(this.data.stream().map(mapper).toList(), total);
    }
}
