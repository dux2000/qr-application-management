package com.example.demo.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private String fullName;
    private String password;
    private LocalDateTime created;
    private String role;
}
