package com.example.demo.domain.filter;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.function.Function;

@Data
public class SearchRequest {
    private SearchFilter searchFilter;
    private Integer page;
    private Integer size;

    public <M, E> SearchResponse<M> fetchAndConvert(JpaSpecificationExecutor<E> repo, Function<E, M> mapper) {
        SpecificationBuilder<E> builder = new SpecificationBuilder<>();
        Pageable page = PageRequest.of(this.page, this.size);
        Page<E> entity = repo.findAll(builder.build(this.searchFilter), page);

        return new SearchResponse<>(entity.map(mapper).toList(), entity.getTotalElements());
    }
}
