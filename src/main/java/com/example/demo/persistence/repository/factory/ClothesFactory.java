package com.example.demo.persistence.repository.factory;

import com.example.demo.domain.model.Clothes;
import com.example.demo.domain.model.CustomerReference;
import com.example.demo.persistence.entity.ClothesEntity;

public class ClothesFactory {
    
    public static Clothes fromClothesEntityToClothes(ClothesEntity clothesEntity) {
        if (clothesEntity == null) return null;

        return new Clothes(
            clothesEntity.getId(), 
            clothesEntity.getName(), 
            clothesEntity.getSize(), 
            clothesEntity.getColor(),
            StatusFactory.fromStatusEntityToStatus(clothesEntity.getStatus()),
            new CustomerReference(clothesEntity.getCustomer().getId())
        );
    }

    public static ClothesEntity fromClothesToClothesEntity(Clothes clothes) {
        if (clothes == null) return null;

        return new ClothesEntity(null, clothes.getName(), clothes.getSize(), clothes.getColor(), null, null);
    }
}
