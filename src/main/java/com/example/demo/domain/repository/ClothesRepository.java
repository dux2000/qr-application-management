package com.example.demo.domain.repository;

import com.example.demo.domain.model.Clothes;

public interface ClothesRepository {
    Clothes createClothes(Clothes clothes);
    void deleteClothes(Long id);
    Clothes changeStatus(Long id);
}
