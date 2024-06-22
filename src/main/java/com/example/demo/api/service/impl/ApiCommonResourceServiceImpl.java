package com.example.demo.api.service.impl;

import com.example.demo.api.dto.CommonResourceDto;
import com.example.demo.api.factory.ApiProductFactory;
import com.example.demo.api.factory.ApiUserFactory;
import com.example.demo.api.service.CommonResourceService;
import com.example.demo.domain.service.ProductService;
import com.example.demo.domain.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ApiCommonResourceServiceImpl implements CommonResourceService {
    private final ProductService productService;
    private final UserService userService;
    @Override
    public CommonResourceDto getCommonResource() {
        CommonResourceDto commonResourceDto = new CommonResourceDto();
        commonResourceDto.setProductTypes(productService.getProductTypes().stream().map(ApiProductFactory::toProductTypeDto).toList());
        commonResourceDto.setUserTypes(userService.getUserTypes().stream().map(ApiUserFactory::toUserTypeDto).toList());

        return commonResourceDto;
    }
}
