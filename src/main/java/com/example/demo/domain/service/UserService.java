package com.example.demo.domain.service;

import com.example.demo.domain.model.User;

public interface UserService {
    User createUser(User user);
    User loginUser(User user);
    User updateUser(Long id, User user);
}
