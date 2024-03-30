package com.example.demo.domain.service;

import com.example.demo.domain.filter.SearchRequest;
import com.example.demo.domain.filter.SearchResponse;
import com.example.demo.domain.model.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(String id);
    List<Product> getProductRevision(String id);
    SearchResponse<Product> getProductsFilter(SearchRequest request);
    Product createProduct(Long userId, Product product);
    Product updateProduct(String id, Long userId, Product product);
}
