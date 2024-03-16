package com.example.demo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CustomerDto {
    private Long id;
    private String fullName;
    private List<ClothesDto> clothes;
    private List<ContactDto> contacts;
}
