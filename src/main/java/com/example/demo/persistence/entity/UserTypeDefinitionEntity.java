package com.example.demo.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_type_definition")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTypeDefinitionEntity {
    @Id
    @Column(name = "code")
    private String code;
    @Column(name = "name", unique = true)
    private String name;
}