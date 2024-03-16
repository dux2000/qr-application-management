package com.example.demo.api.command;

import lombok.Data;

import java.util.List;

@Data
public class CustomerCommand {
    private String fullName;
    private List<ClothesCommand> clothes;
    private List<ContactCommand> contacts;
}
