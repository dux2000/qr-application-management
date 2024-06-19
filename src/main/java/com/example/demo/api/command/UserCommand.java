package com.example.demo.api.command;

import lombok.Data;

@Data
public class UserCommand {
    private String username;
    private String fullName;
    private String password;
    private String role;
    private Boolean update;
}
