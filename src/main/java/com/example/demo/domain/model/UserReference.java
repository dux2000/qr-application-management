package com.example.demo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserReference {
    private Long id;
    private String fullName;
    private String role;

    public UserReference(Long id) {
        this.id = id;
    }
}
