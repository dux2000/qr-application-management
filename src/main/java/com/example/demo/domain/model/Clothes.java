package com.example.demo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Clothes {
    private Long id;
    private String name;
    private String size;
    private String color;
    private Status status;
    private CustomerReference customer;
}
