package com.example.demo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Customer {
    private Long id;
    private String fullName;
    private List<Clothes> clothes;
    private List<Contact> contacts;
}
