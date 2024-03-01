package com.example.demo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.api.command.ClothesCommand;
import com.example.demo.api.dto.ClothesDto;
import com.example.demo.api.service.ApiClothesService;

@RestController
@RequestMapping("/api/v1/clothes")
public class ClothesController {
    
    @Autowired
    private ApiClothesService apiClothesService;

    @GetMapping("/{id}")
    public ClothesDto getClothesWithId(@PathVariable Long id) {
        return apiClothesService.getClothesWithId(id);
    }

    @PostMapping("/{customerId}")
    public ClothesDto createClothes(@PathVariable Long customerId, @RequestBody ClothesCommand clothes) {
        return apiClothesService.createClothes(customerId, clothes);
    }

    @DeleteMapping("/{id}")
    public void deleteClothes(@PathVariable Long id) {
        apiClothesService.deleteClothes(id);
    }

    @PutMapping("/{id}/{nextStatusCode}")
    public ClothesDto changeStatus(@PathVariable Long id, @PathVariable String nextStatusCode) {
        return apiClothesService.changeStatus(id, nextStatusCode);
    }
}
