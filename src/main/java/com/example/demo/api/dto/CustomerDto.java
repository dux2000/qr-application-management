package com.example.demo.api.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String name; 
    private String surname;
    private List<ClothesDto> clothes;
    private List<ContactDto> contacts;
}
