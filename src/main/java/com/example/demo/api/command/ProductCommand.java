package com.example.demo.api.command;

import lombok.Data;

import java.util.List;

@Data
public class ProductCommand {
    private String name;
    private String description;
    private Long customerId;
    private Long currentUserId;
    private String status;
    private List<CharacteristicCommand> characteristics;
}
