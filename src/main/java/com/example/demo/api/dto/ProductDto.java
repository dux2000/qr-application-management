package com.example.demo.api.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ProductDto {
    private String id;
    private String name;
    private String description;
    private List<CharacteristicDto> characteristics;
    private StatusDto status;
    private CustomerReferenceDto customer;
    private UserReferenceDto currentUser;
    private LocalDateTime created;
    private LocalDateTime updated;
    private UserReferenceDto createdBy;
    private UserReferenceDto updatedBy;
}
