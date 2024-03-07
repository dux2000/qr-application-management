package com.example.demo.api.service;

import com.example.demo.api.command.UserCommand;
import com.example.demo.api.dto.UserDto;

public interface ApiUserService {
    UserDto createUser(UserCommand userCommand);
    UserDto loginUser(UserCommand userCommand);
    UserDto updateUser(Long id, UserCommand updatedUser);
}
