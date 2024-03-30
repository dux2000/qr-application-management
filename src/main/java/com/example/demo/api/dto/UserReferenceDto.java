package com.example.demo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserReferenceDto {
    private Long id;
    private String fullName;
    private String role;
}
