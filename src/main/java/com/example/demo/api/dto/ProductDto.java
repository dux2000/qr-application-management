package com.example.demo.api.dto;

import com.example.demo.domain.model.Characteristic;
import com.example.demo.domain.model.CustomerReference;
import com.example.demo.domain.model.Status;
import com.example.demo.domain.model.UserReference;
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
    private UserReferenceDto user;
    private LocalDateTime created;
    private LocalDateTime updated;
}
