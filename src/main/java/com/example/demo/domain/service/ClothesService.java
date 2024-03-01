package com.example.demo.domain.service;

import com.example.demo.domain.model.Clothes;

public interface ClothesService {
    Clothes getClothesWithId(Long id);
    Clothes createClothes(Long customerId, Clothes clothes);
    void deleteClothes(Long id);
    Clothes changeStatus(Long id, String nextStatusCode);
}
