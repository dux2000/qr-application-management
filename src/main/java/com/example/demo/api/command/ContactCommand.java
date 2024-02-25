package com.example.demo.api.command;

import lombok.Data;

@Data
public class ContactCommand {
    private String type;
    private String contactInfo;
}
