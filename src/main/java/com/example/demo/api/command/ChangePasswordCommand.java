package com.example.demo.api.command;

import lombok.Data;

@Data
public class ChangePasswordCommand {
    private String oldPassword;
    private String newPassword;
}
