package com.example.demo.domain.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Product {
    private String id;
    private String name;
    private String description;
    private List<Characteristic> characteristics;
    private Status status;
    private ProductType type;
    private CustomerReference customer;
    private UserReference currentUser;
    private LocalDateTime created;
    private UserReference createdBy;
    private LocalDateTime updated;
    private UserReference updatedBy;
}
