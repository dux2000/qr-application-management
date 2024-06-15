package com.example.demo.api.service;


import com.example.demo.api.command.ProductCommand;
import com.example.demo.api.dto.ProductDto;
import com.example.demo.api.dto.ProductTypeDto;
import com.example.demo.domain.filter.SearchRequest;
import com.example.demo.domain.filter.SearchResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ApiProductService {
    ProductDto getProductById(String id);
    List<ProductDto> getProductRevision(String id);
    SearchResponse<ProductDto> getProductsFilter(SearchRequest request);
    ProductDto createProduct(Long userId, ProductCommand product);
    ProductDto updateProduct(String productId, Long userId, ProductCommand product);
    List<ProductDto> getProductRevision(SearchRequest request);
    List<ProductTypeDto> getProductTypes();
    void deleteProduct(String id);
}
