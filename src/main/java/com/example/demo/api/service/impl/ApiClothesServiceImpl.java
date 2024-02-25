package com.example.demo.api.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.api.command.ClothesCommand;
import com.example.demo.api.dto.ClothesDto;
import com.example.demo.api.factory.ApiClothesFactory;
import com.example.demo.api.service.ApiClothesService;
import com.example.demo.domain.service.ClothesService;

@Service
public class ApiClothesServiceImpl implements ApiClothesService {
    
    @Autowired
    private ClothesService domainClothesService;

    @Override
    public ClothesDto createClothes(Long customerId, ClothesCommand clothes) {
        return ApiClothesFactory.fromClothesToDto(domainClothesService.createClothes(customerId, ApiClothesFactory.fromCommandToClothes(clothes)));
    }

    @Override
    public void deleteClothes(Long id) {
        domainClothesService.deleteClothes(id);
    }

    @Override
    public ClothesDto changeStatus(Long id) {
        return ApiClothesFactory.fromClothesToDto(domainClothesService.changeStatus(id));
    }
    
}
