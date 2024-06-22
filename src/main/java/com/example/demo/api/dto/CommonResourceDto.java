package com.example.demo.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class CommonResourceDto {
    private List<ProductTypeDto> productTypes;
    private List<UserTypeDto> userTypes;
}
