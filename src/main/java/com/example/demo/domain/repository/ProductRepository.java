package com.example.demo.domain.repository;


import com.example.demo.domain.filter.SearchRequest;
import com.example.demo.domain.filter.SearchResponse;
import com.example.demo.domain.model.Product;

public interface ProductRepository {
    SearchResponse<Product> getProductsFilter(SearchRequest request);

    Product createProduct(Product product);
}
