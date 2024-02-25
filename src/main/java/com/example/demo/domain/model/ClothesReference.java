package com.example.demo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ClothesReference {
    private Long id;
    private String name;
    private String size;
    private String color;
}
