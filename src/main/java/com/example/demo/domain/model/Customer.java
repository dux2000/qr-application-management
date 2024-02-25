package com.example.demo.domain.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {
    private Long id;
    private String name;
    private String surname;
    private List<Clothes> clothes;
    private List<Contact> contacts;
}
