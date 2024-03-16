package com.example.demo.domain.service;

import com.example.demo.domain.model.Product;

public interface ProductService {
    Product createProduct(Long customerId, Long userId, Product product);
}
