package com.example.demo.api.command;

import lombok.Data;

import java.util.List;

@Data
public class CustomerCommand {
    private String name;
    private String surname;
    private List<ClothesCommand> clothes;
    private List<ContactCommand> contacts;
}
