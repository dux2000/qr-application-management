package com.example.demo.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "\"user\"")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private Long id;

    @Column(name = "fullname")
    private String fullName;
    private String username;
    private String password;
    private String role;
    private LocalDateTime created;
    private LocalDateTime updated;
    private LocalDateTime deleted;
    private Boolean update;
}
