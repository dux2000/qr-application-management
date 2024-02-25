package com.example.demo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Contact {
    private Long id;
    private String type;
    private String contactInfo;
    private CustomerReference customer;
}
