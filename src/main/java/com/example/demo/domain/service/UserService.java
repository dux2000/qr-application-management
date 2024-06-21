package com.example.demo.domain.service;

import com.example.demo.domain.filter.SearchRequest;
import com.example.demo.domain.filter.SearchResponse;
import com.example.demo.domain.model.User;

public interface UserService {
    User getUser(Long id);
    SearchResponse<User> getUsers(SearchRequest request);
    User createUser(User user);
    User loginUser(User user);
    User updateUser(Long id, User user);
    User changePassword(Long id, String oldPassword, String newPassword);
}
