package com.example.demo.domain.repository;

import com.example.demo.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    User createUser(User user);
    User getUserByUsername(String username);
    User updateUser(User user);
}
