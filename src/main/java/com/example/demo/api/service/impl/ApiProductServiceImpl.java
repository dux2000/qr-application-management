package com.example.demo.api.service.impl;

import com.example.demo.api.command.ProductCommand;
import com.example.demo.api.dto.ProductDto;
import com.example.demo.api.factory.ApiProductFactory;
import com.example.demo.api.service.ApiProductService;
import com.example.demo.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiProductServiceImpl implements ApiProductService {

    private final ProductService productService;
    @Override
    public ProductDto createProduct(Long customerId, Long userId, ProductCommand product) {
        return ApiProductFactory.fromProductToDto(productService.createProduct(customerId, userId, ApiProductFactory.fromProductCommandToProduct(product)));
    }
}
