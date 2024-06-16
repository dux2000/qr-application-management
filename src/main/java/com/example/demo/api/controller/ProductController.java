package com.example.demo.api.controller;

import com.example.demo.api.command.ProductCommand;
import com.example.demo.api.dto.ProductDto;
import com.example.demo.api.dto.ProductTypeDto;
import com.example.demo.api.service.ApiProductService;
import com.example.demo.domain.filter.SearchRequest;
import com.example.demo.domain.filter.SearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ApiProductService apiProductService;

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable String id) {
        return apiProductService.getProductById(id);
    }

    @GetMapping("/revision/{id}")
    public List<ProductDto> getProductRevision(@PathVariable String id) {
        return apiProductService.getProductRevision(id);
    }

    @PostMapping("/filter")
    public SearchResponse<ProductDto> getProductsFilter(@RequestBody SearchRequest request) {
        return apiProductService.getProductsFilter(request);
    }
    @PostMapping("/{userId}")
    public ProductDto createProduct(@PathVariable Long userId, @RequestBody ProductCommand product) {
        return apiProductService.createProduct(userId, product);
    }

    @PutMapping("/{productId}/{userId}")
    public ProductDto updateProduct(@PathVariable String productId, @PathVariable Long userId, @RequestBody ProductCommand product) {
        return apiProductService.updateProduct(productId, userId, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        apiProductService.deleteProduct(id);
    }

    @PostMapping("/revision/filter")
    public List<ProductDto> getProductRevisionForUser(@RequestBody SearchRequest request) {
        return apiProductService.getProductRevision(request);
    }

    @GetMapping("/types")
    public List<ProductTypeDto> getProductTypes() {
        return apiProductService.getProductTypes();
    }
}
