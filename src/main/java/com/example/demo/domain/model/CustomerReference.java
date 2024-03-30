package com.example.demo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerReference {
    private Long id;
    private String fullName;
    public CustomerReference(Long id) {
        this.id = id;
    }
}
