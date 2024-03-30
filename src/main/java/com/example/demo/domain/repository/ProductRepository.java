package com.example.demo.domain.repository;


import com.example.demo.domain.filter.SearchRequest;
import com.example.demo.domain.filter.SearchResponse;
import com.example.demo.domain.model.Product;

import java.util.List;

public interface ProductRepository {
    Product getProductById(String id);
    List<Product> getProductRevision(String id);
    SearchResponse<Product> getProductsFilter(SearchRequest request);

    Product createProduct(Product product);
    Product updateProduct(Product product);
}
