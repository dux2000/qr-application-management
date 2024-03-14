package com.example.demo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Customer {
    private Long id;
    private String name;
    private String surname;
    private List<Clothes> clothes;
    private List<Contact> contacts;
}
