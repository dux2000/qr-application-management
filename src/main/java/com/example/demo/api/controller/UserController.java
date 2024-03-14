package com.example.demo.api.controller;

import com.example.demo.api.command.UserCommand;
import com.example.demo.api.dto.UserDto;
import com.example.demo.api.service.ApiUserService;
import com.example.demo.domain.filter.SearchRequest;
import com.example.demo.domain.filter.SearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final ApiUserService apiUserService;
    @PostMapping("/filter")
    public SearchResponse<UserDto> getUsers(@RequestBody SearchRequest request) {
        return apiUserService.getUsers(request);
    }
    @PostMapping("")
    public UserDto createUser(@RequestBody UserCommand user) {
        return apiUserService.createUser(user);
    }

    @PostMapping("/login")
    public UserDto loginUser(@RequestBody UserCommand user) {
        return apiUserService.loginUser(user);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody UserCommand updatedUser) {
        return apiUserService.updateUser(id, updatedUser);
    }

}
