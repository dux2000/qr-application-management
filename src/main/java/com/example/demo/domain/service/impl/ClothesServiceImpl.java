package com.example.demo.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.model.Clothes;
import com.example.demo.domain.model.CustomerReference;
import com.example.demo.domain.repository.ClothesRepository;
import com.example.demo.domain.service.ClothesService;

@Service
public class ClothesServiceImpl implements ClothesService {
    
    @Autowired
    private ClothesRepository clothesRepository;

    @Override
    public Clothes getClothesWithId(Long id) {
        return clothesRepository.getClothesWithId(id);
    }

    @Override
    public Clothes createClothes(Long customerId, Clothes clothes) {
        clothes.setCustomer(new CustomerReference(customerId));
        return clothesRepository.createClothes(clothes);
    }

    @Override
    public void deleteClothes(Long id) {
        clothesRepository.deleteClothes(id);
    }

    @Override
    public Clothes changeStatus(Long id, String nextStatusCode) {
        return clothesRepository.changeStatus(id, nextStatusCode);
    }
    
}
