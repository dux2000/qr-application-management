package com.example.demo.api.service;

import com.example.demo.api.command.UserCommand;
import com.example.demo.api.dto.UserDto;
import com.example.demo.domain.filter.SearchCriteriaCommand;
import org.springframework.data.domain.Page;

public interface ApiUserService {
    Page<UserDto> getUsers(SearchCriteriaCommand searchCriteriaCommand, Integer pageNum, Integer pageSize);
    UserDto createUser(UserCommand userCommand);
    UserDto loginUser(UserCommand userCommand);
    UserDto updateUser(Long id, UserCommand updatedUser);
}
