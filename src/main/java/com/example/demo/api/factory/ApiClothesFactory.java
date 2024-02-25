package com.example.demo.api.factory;

import com.example.demo.api.command.ClothesCommand;
import com.example.demo.api.dto.ClothesDto;
import com.example.demo.domain.model.Clothes;

public class ApiClothesFactory {
     public static ClothesDto fromClothesToDto(Clothes clothes) {
        if (clothes == null) 
            return null;
        
        return new ClothesDto(
            clothes.getId(), 
            clothes.getName(), 
            clothes.getSize(), 
            clothes.getColor(),
            ApiStatusFactory.fromStatusToDto(clothes.getStatus())
        );
    }
    
    public static Clothes fromCommandToClothes(ClothesCommand clothesCommand) {
        if (clothesCommand == null) {
            return null;
        }

        return new Clothes(null, clothesCommand.getName(), clothesCommand.getSize(), clothesCommand.getColor(), null, null);
    }
}
