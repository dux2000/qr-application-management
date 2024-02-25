package com.example.demo.api.command;

import java.util.List;

import lombok.Data;

@Data
public class CustomerCommand {
    private String name;
    private String surname;
    private List<ClothesCommand> clothes;
    private List<ContactCommand> contacts;
}
