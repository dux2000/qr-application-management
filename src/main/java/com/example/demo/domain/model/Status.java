package com.example.demo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Status {
    private String code;
    private String description;
    private String name;
    private List<StatusTransition> transitions;
    public Status(String code) {
        this.code = code;
    }
}
