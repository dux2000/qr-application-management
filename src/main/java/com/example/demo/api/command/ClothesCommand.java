package com.example.demo.api.command;

import lombok.Data;

@Data
public class ClothesCommand {
    private Long id;
    private String name;
    private String size;
    private String color;
}
