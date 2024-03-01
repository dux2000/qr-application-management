package com.example.demo.api.service;

import com.example.demo.api.command.ClothesCommand;
import com.example.demo.api.dto.ClothesDto;

public interface ApiClothesService {
    ClothesDto getClothesWithId(Long id);
    ClothesDto createClothes(Long customerId, ClothesCommand clothes);
    void deleteClothes(Long id);
    ClothesDto changeStatus(Long id, String nextStatusCode);
}
