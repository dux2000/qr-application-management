package com.example.demo.domain.model;

import lombok.Data;

@Data
public class Characteristic {
    private String id;
    private String code;
    private String globalCode;
    private String value;
    private ProductReference product;
}
