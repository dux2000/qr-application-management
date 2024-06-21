package com.example.demo.api.command;

import lombok.Data;

import java.util.List;

@Data
public class UserCommand {
    private String username;
    private String fullName;
    private String password;
    private List<UserTypeCommand> types;
    private Boolean update;
}
