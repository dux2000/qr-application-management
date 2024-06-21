package com.example.demo.api.service.impl;

import com.example.demo.api.command.ChangePasswordCommand;
import com.example.demo.api.command.UserCommand;
import com.example.demo.api.dto.UserDto;
import com.example.demo.api.factory.ApiCustomerFactory;
import com.example.demo.api.factory.ApiUserFactory;
import com.example.demo.api.service.ApiUserService;
import com.example.demo.domain.filter.SearchRequest;
import com.example.demo.domain.filter.SearchResponse;
import com.example.demo.domain.model.User;
import com.example.demo.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiUserServiceImpl implements ApiUserService {
    private final UserService userService;

    @Override
    public UserDto getUser(Long id) {
        return ApiUserFactory.fromUserToDto(userService.getUser(id));
    }

    @Override
    public SearchResponse<UserDto> getUsers(SearchRequest request) {
        return userService.getUsers(request).convert(ApiUserFactory::fromUserToDto);
    }

    @Override
    public UserDto createUser(UserCommand userCommand) {
        return ApiUserFactory.fromUserToDto(userService.createUser(ApiUserFactory.fromUserCommandToUser(userCommand)));
    }

    @Override
    public UserDto loginUser(UserCommand userCommand) {
        return ApiUserFactory.fromUserToDto(userService.loginUser(ApiUserFactory.fromUserCommandToUser(userCommand)));
    }

    @Override
    public UserDto updateUser(Long id, UserCommand updatedUserCommand) {
        return ApiUserFactory.fromUserToDto(userService.updateUser(id, ApiUserFactory.fromUserCommandToUser(updatedUserCommand)));
    }

    @Override
    public UserDto changePassword(Long id, ChangePasswordCommand changePasswordCommand) {
        return ApiUserFactory.fromUserToDto(userService.changePassword(id, changePasswordCommand.getOldPassword(), changePasswordCommand.getNewPassword()));
    }
}
