package com.example.demo.api.controller;

import com.example.demo.api.command.ProductCommand;
import com.example.demo.api.dto.ProductDto;
import com.example.demo.api.service.ApiProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ApiProductService apiProductService;

    @PostMapping("/{customerId}/{userId}")
    public ProductDto createProduct(@PathVariable Long customerId, @PathVariable Long userId, @RequestBody ProductCommand product) {
        return apiProductService.createProduct(customerId, userId, product);
    }
}
