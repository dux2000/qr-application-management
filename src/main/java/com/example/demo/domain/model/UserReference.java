package com.example.demo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserReference {
    private Long id;
    private String fullName;

    public UserReference(Long id) {
        this.id = id;
    }
}
