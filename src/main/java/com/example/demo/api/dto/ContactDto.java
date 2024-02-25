package com.example.demo.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ContactDto {
    private Long id;
    private String type;
    private String contactInfo;
}
