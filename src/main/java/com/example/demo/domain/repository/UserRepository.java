package com.example.demo.domain.repository;

import com.example.demo.domain.filter.SearchRequest;
import com.example.demo.domain.filter.SearchResponse;
import com.example.demo.domain.model.User;
import com.example.demo.domain.model.UserType;

import java.util.List;


public interface UserRepository {
    SearchResponse<User> getUsers(SearchRequest request);
    User createUser(User user);
    User getUserByUsername(String username);
    User getUserById(Long id);
    User updateUser(User user);
    List<UserType> getUserTypes();
}
