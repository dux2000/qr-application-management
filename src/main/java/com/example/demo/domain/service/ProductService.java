package com.example.demo.domain.service;

import com.example.demo.domain.filter.SearchRequest;
import com.example.demo.domain.filter.SearchResponse;
import com.example.demo.domain.model.Product;

public interface ProductService {
    SearchResponse<Product> getProductsFilter(SearchRequest request);
    Product createProduct(Long customerId, Long userId, Product product);
}
