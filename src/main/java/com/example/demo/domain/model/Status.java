package com.example.demo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Status {
    private String code;
    private String description;
    private String name;
    public Status(String code) {
        this.code = code;
    }
}
