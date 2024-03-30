package com.example.demo.api.service.impl;

import com.example.demo.api.command.ProductCommand;
import com.example.demo.api.dto.ProductDto;
import com.example.demo.api.factory.ApiProductFactory;
import com.example.demo.api.service.ApiProductService;
import com.example.demo.domain.filter.SearchRequest;
import com.example.demo.domain.filter.SearchResponse;
import com.example.demo.domain.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApiProductServiceImpl implements ApiProductService {

    private final ProductService productService;

    @Override
    public ProductDto getProductById(String id) {
        return ApiProductFactory.fromProductToDto(productService.getProductById(id));
    }

    @Override
    public List<ProductDto> getProductRevision(String id) {
        return productService.getProductRevision(id).stream().map(ApiProductFactory::fromProductToDto).toList();
    }

    @Override
    public SearchResponse<ProductDto> getProductsFilter(SearchRequest request) {
        return productService.getProductsFilter(request).convert(ApiProductFactory::fromProductToDto);
    }

    @Override
    public ProductDto createProduct(Long userId, ProductCommand product) {
        return ApiProductFactory.fromProductToDto(productService.createProduct(userId, ApiProductFactory.fromProductCommandToProduct(product)));
    }

    @Override
    public ProductDto updateProduct(String productId, Long userId, ProductCommand product) {
        return ApiProductFactory.fromProductToDto(productService.updateProduct(productId, userId, ApiProductFactory.fromProductCommandToProduct(product)));
    }
}
