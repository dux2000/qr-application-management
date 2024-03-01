package com.example.demo.domain.repository;

import com.example.demo.domain.model.Clothes;

public interface ClothesRepository {
    Clothes getClothesWithId(Long id);
    Clothes createClothes(Clothes clothes);
    void deleteClothes(Long id);
    Clothes changeStatus(Long id, String nextStatusCode);
}
