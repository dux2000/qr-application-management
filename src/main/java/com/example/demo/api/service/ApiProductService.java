package com.example.demo.api.service;


import com.example.demo.api.command.ProductCommand;
import com.example.demo.api.dto.ProductDto;
import com.example.demo.domain.filter.SearchRequest;
import com.example.demo.domain.filter.SearchResponse;

public interface ApiProductService {
    SearchResponse<ProductDto> getProductsFilter(SearchRequest request);
    ProductDto createProduct(Long customerId, Long userId, ProductCommand product);
}
