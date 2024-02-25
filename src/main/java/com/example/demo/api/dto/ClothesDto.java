package com.example.demo.api.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClothesDto {
    private Long id;
    private String name;
    private String size;
    private String color;
    private StatusDto status;
}
