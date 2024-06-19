package com.example.demo.api.service;

import com.example.demo.api.command.ChangePasswordCommand;
import com.example.demo.api.command.UserCommand;
import com.example.demo.api.dto.UserDto;
import com.example.demo.domain.filter.SearchRequest;
import com.example.demo.domain.filter.SearchResponse;

public interface ApiUserService {
    SearchResponse<UserDto> getUsers(SearchRequest request);
    UserDto createUser(UserCommand userCommand);
    UserDto loginUser(UserCommand userCommand);
    UserDto updateUser(Long id, UserCommand updatedUser);
    UserDto changePassword(Long id, ChangePasswordCommand changePasswordCommand);
}
