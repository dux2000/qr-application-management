package com.example.demo.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String fullName;
    private List<UserTypeDto> types;
    private LocalDateTime created;
    private Boolean update;
}
