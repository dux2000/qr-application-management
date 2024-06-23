package com.example.demo.api.service.impl;

import com.example.demo.api.command.ChangePasswordCommand;
import com.example.demo.api.command.UserCommand;
import com.example.demo.api.dto.UserDto;
import com.example.demo.api.factory.ApiCustomerFactory;
import com.example.demo.api.factory.ApiUserFactory;
import com.example.demo.api.service.ApiUserService;
import com.example.demo.config.JwtService;
import com.example.demo.domain.filter.SearchRequest;
import com.example.demo.domain.filter.SearchResponse;
import com.example.demo.domain.model.User;
import com.example.demo.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApiUserServiceImpl implements ApiUserService {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

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
        User user = userService.loginUser(ApiUserFactory.fromUserCommandToUser(userCommand));
        var jwtToken = jwtService.generateToken(user);

        UserDto userDto = ApiUserFactory.fromUserToDto(user);
        userDto.setToken(jwtToken);
        return userDto;
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
