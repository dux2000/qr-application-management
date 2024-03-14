package com.example.demo.api.service.impl;

import com.example.demo.api.command.UserCommand;
import com.example.demo.api.dto.UserDto;
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
    public SearchResponse<UserDto> getUsers(SearchRequest request) {
        SearchResponse<User> response = userService.getUsers(request);
        SearchResponse<UserDto> responseDto = new SearchResponse<>();
        responseDto.setData(response.getData().stream().map(ApiUserFactory::fromUserToDto).toList());
        responseDto.setTotal(response.getTotal());
        return responseDto;
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
}
