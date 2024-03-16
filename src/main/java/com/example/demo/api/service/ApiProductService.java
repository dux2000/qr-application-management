package com.example.demo.api.service;


import com.example.demo.api.command.ProductCommand;
import com.example.demo.api.dto.ProductDto;

public interface ApiProductService {

    ProductDto createProduct(Long customerId, Long userId, ProductCommand product);
}
