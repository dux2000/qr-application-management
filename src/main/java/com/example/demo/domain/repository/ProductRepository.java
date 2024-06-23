package com.example.demo.domain.repository;


import com.example.demo.domain.filter.SearchRequest;
import com.example.demo.domain.filter.SearchResponse;
import com.example.demo.domain.model.Product;
import com.example.demo.domain.model.ProductType;
import com.example.demo.domain.model.StatusTransition;

import java.util.List;
import java.util.Set;

public interface ProductRepository {
    Product getProductById(String id);
    List<Product> getProductRevision(String id);
    SearchResponse<Product> getProductsFilter(SearchRequest request);

    Product createProduct(Product product);
    Product updateProduct(Product product);
    List<Product> getProductRevision(SearchRequest request);
    List<ProductType> getProductTypes();
    void deleteProduct(String id);
    List<StatusTransition> getProductStatusTransitionForUserType(Set<String> userTypes, Set<String> statusCodes);
}
