package com.example.demo.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.api.command.ClothesCommand;
import com.example.demo.api.dto.ClothesDto;
import com.example.demo.api.service.ApiClothesService;

@RestController
@RequestMapping("/api/v1/clothes")
public class ClothesController {
    
    @Autowired
    private ApiClothesService apiClothesService;

    @PostMapping("/{customerId}")
    public ClothesDto createClothes(@PathVariable Long customerId, @RequestBody ClothesCommand clothes) {
        return apiClothesService.createClothes(customerId, clothes);
    }

    @DeleteMapping("/{id}")
    public void deleteClothes(@PathVariable Long id) {
        apiClothesService.deleteClothes(id);
    }

    @PutMapping("/{id}/next-status")
    public ClothesDto changeStatus(@PathVariable Long id) {
        return apiClothesService.changeStatus(id);
    }
}
